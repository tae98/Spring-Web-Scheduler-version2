package com.sparta.springwebscheduler.service;

import com.sparta.springwebscheduler.dto.PageResponseDto;
import com.sparta.springwebscheduler.dto.ScheduleDetailResponseDto;
import com.sparta.springwebscheduler.dto.ScheduleDto.ScheduleRequestDto;
import com.sparta.springwebscheduler.dto.ScheduleDto.ScheduleResponseDto;
import com.sparta.springwebscheduler.entity.Schedule;
import com.sparta.springwebscheduler.entity.Storage;
import com.sparta.springwebscheduler.entity.User;
import com.sparta.springwebscheduler.entity.UserRoleEnum;
import com.sparta.springwebscheduler.repository.ScheduleRepository;
import com.sparta.springwebscheduler.repository.StorageRepository;
import com.sparta.springwebscheduler.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final StorageRepository storageRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequest) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(scheduleRequest);
        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);
        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public List<PageResponseDto> getScheduleList(int page, int size) {
        //Pageable interface 사용하여 pageagle 알맞은 page, size 값을 가진 객체 생성
        Pageable pageable = PageRequest.of(page, size);
        //Schedule 을 포함한 page 리스트 생성
        Page<Schedule> scheduleList;
        //생성한 리스트에 수정일 내림차순으로 모든 schedule 주입 (JpaRepository custom method)
        scheduleList = scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);
        //Page<Schedule> -> List<PageResponseDto> 로 map 을 이용하여 변경
        return scheduleList.map(schedule -> new PageResponseDto(
                schedule.getCommentList().size(),
                schedule.getUser_id(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getModifiedAt(),
                schedule.getCreatedAt()
        )).getContent();
    }

    //변경 사항이 있음으로 Transactional value 변경
    @Transactional
    public Schedule updateSchedule(Long id, ScheduleRequestDto scheduleRequest, User user) {
        if(user.getRole().equals(UserRoleEnum.USER)){
            throw new IllegalArgumentException("사용자 권한이 없습니다");
        }
        //알맞은 schedule db 에서 찾기
        Schedule schedule = getScheduleById(id);
        //해당 schedule 을 Schedule 클래스 내부 method update 를 사용하여 ScheduleRequestDto 내용대로 변경
        schedule.update(scheduleRequest);
        //변경된 schedule 리턴
        return schedule;
    }

    public void setUserToSchedule(Long id, Long user_id) {
        Schedule schedule = getScheduleById(id);

        User user = userRepository.findById(user_id).orElseThrow(
                () -> {
                    return new IllegalArgumentException("선택한 유저는 존재하지 않습니다.");
                });

        Storage storage = new Storage();
        storage.setUser(user);
        storage.setSchedule(schedule);

        storageRepository.save(storage);
    }

    public Long deleteSchedule(Long id, User user) {
        if(user.getRole().equals(UserRoleEnum.USER)){
            throw new IllegalArgumentException("사용자 권한이 없습니다");
        }
        //알맞은 schedule db 에서 찾기
        Schedule deleteSchedule = getScheduleById(id);
        //JpaRepository Inteface 내포한 delete를 사용하여 해당 schedule 삭제
        scheduleRepository.delete(deleteSchedule);
        //삭제된 schedule id 리턴
        return id;
    }

    public ScheduleDetailResponseDto getScheduleByIdDedtail(Long id) {
        Schedule schedule = getScheduleById(id);
        User user = userRepository.findById(schedule.getUser_id()).orElseThrow(
                ()->new IllegalArgumentException("해당 유저가 존재하지않습니다.")
        );
        ScheduleDetailResponseDto scheduleDetailResponse = new ScheduleDetailResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents()
        );
        return scheduleDetailResponse;
    }

    public Schedule getScheduleById(Long id) {
        //db상에 해당 id 를 가진 스캐쥴 return 없을 시 Exception 처리
        return scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정는 존재하지 않습니다.")
        );
    }
}
