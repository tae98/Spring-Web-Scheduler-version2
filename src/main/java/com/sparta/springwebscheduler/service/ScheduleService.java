package com.sparta.springwebscheduler.service;

import com.sparta.springwebscheduler.dto.ScheduleRequestDto;
import com.sparta.springwebscheduler.dto.ScheduleResponseDto;
import com.sparta.springwebscheduler.entity.Schedule;
import com.sparta.springwebscheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

//    public ScheduleResponseDto getScheduleById(Long id) {
//        return scheduleRepository.findBy(id).stream().map(Schedule::new);
//    }
}
