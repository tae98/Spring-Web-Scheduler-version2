package com.sparta.springwebscheduler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final StorageRepository storageRepository;
    private final RestTemplate restTemplate;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository, StorageRepository storageRepository, RestTemplateBuilder restTemplate) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.storageRepository = storageRepository;
        this.restTemplate = restTemplate.build();
    }

    @Transactional // weather data 삽입 위해 필요함
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequest) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(scheduleRequest);
        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);
        //생성일로 받아온 날짜 weather 구한뒤 set
        String weather = getWeather(schedule.getCreatedAt());
        schedule.setWeather(weather);
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
                    throw  new IllegalArgumentException("선택한 유저는 존재하지 않습니다.");
                });
        if(!schedule.getUser_id().equals(user.getId())){
            throw new IllegalArgumentException("작성 유저만 담당 유저를 배치할수 있습니다.");
        }
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

    public String getWeather(LocalDateTime date) {
        // 날짜를 "MM-dd" 형식으로 포맷팅
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MM-dd"));

        URI uri = UriComponentsBuilder
                .fromUriString("https://f-api.github.io")
                .path("/f-api/weather.json")
                .queryParam("date", formattedDate)
                .encode()
                .build()
                .toUri();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        String responseBody = responseEntity.getBody();

        // Jackson ObjectMapper를 사용하여 JSON 문자열을 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> weatherData = null;
        try {
            weatherData = objectMapper.readValue(responseBody, new TypeReference<List<Map<String, String>>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // 예외 처리: 로그를 남기거나 기본 값을 반환할 수 있습니다.
            return "Error processing weather data";
        }

        // 날짜에 맞는 날씨 정보를 찾기
        for (Map<String, String> entry : weatherData) {
            if (entry.get("date").equals(formattedDate)) {
                return entry.get("weather");
            }
        }

        // 날짜에 맞는 날씨 정보가 없는 경우
        return "No weather data available for the specified date";
    }

    public Schedule getScheduleById(Long id) {
        //db상에 해당 id 를 가진 스캐쥴 return 없을 시 Exception 처리
        return scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정는 존재하지 않습니다.")
        );
    }
}
