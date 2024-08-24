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
@RequestMapping("/api/schedule")
public class SchedulerController {

    private final ScheduleService scheduleService;


    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequest){
        return scheduleService.createSchedule(scheduleRequest);
    }

    @GetMapping("/{id}")
    public Schedule getScheduleById(@PathVariable Long id){
        return scheduleService.getScheduleById(id);
    }

    @GetMapping
    public List<PageResponseDto> getScheduleList(
            @RequestParam("page") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
            ){
        return scheduleService.getScheduleList(page -1,size);
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequest){
        return scheduleService.updateSchedule(id, scheduleRequest);
    }

    @DeleteMapping("/{id}")
    public Long deleteSchedule(@PathVariable Long id){
        return scheduleService.deleteSchedule(id);
    }

    @PutMapping("/{id}/{user_id}")
    public void setUserToSchedule(@PathVariable Long id, @PathVariable Long user_id){
        scheduleService.setUserToSchedule(id, user_id);
    }

}
