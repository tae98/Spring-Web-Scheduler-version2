package com.sparta.springwebscheduler.service;

import com.sparta.springwebscheduler.dto.ScheduleRequestDto;
import com.sparta.springwebscheduler.dto.ScheduleResponseDto;
import com.sparta.springwebscheduler.entity.Schedule;
import com.sparta.springwebscheduler.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequest) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(scheduleRequest);
        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);
        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public List<Schedule> getScheduleList() {
        return scheduleRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Schedule updateSchedule(Long id, ScheduleRequestDto scheduleRequest) {
        Schedule schedule = getScheduleById(id);
        schedule.update(scheduleRequest);
        return schedule;
    }

    public Schedule getScheduleById(long id){
        return scheduleRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
