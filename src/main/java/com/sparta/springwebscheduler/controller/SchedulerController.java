package com.sparta.springwebscheduler.controller;

import com.sparta.springwebscheduler.dto.PageResponseDto;
import com.sparta.springwebscheduler.dto.ScheduleDto.ScheduleRequestDto;
import com.sparta.springwebscheduler.dto.ScheduleDto.ScheduleResponseDto;
import com.sparta.springwebscheduler.entity.Schedule;
import com.sparta.springwebscheduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SchedulerController {

    private final ScheduleService scheduleservice;


    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequest){
        return scheduleservice.createSchedule(scheduleRequest);
    }

    @GetMapping("/schedule/{id}")
    public Schedule getScheduleById(@PathVariable Long id){
        return scheduleservice.getScheduleById(id);
    }

    @GetMapping("/schedule")
    public List<PageResponseDto> getScheduleList(
            @RequestParam("page") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
            ){
        return scheduleservice.getScheduleList(page -1,size);
    }

    @PutMapping("/schedule/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequest){
        return scheduleservice.updateSchedule(id, scheduleRequest);
    }

    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id){
        return scheduleservice.deleteSchedule(id);
    }


}
