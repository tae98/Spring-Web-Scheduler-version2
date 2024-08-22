package com.sparta.springwebscheduler.controller;

import com.sparta.springwebscheduler.dto.ScheduleRequestDto;
import com.sparta.springwebscheduler.dto.ScheduleResponseDto;
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
    public List<Schedule> getScheduleList(){
        return scheduleservice.getScheduleList();
    }

    @PutMapping("/schedule/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequest){
        return scheduleservice.updateSchedule(id, scheduleRequest);
    }


}
