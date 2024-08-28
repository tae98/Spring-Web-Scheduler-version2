package com.sparta.springwebscheduler.controller;

import com.sparta.springwebscheduler.dto.PageResponseDto;
import com.sparta.springwebscheduler.dto.ScheduleDetailResponseDto;
import com.sparta.springwebscheduler.dto.ScheduleDto.ScheduleRequestDto;
import com.sparta.springwebscheduler.dto.ScheduleDto.ScheduleResponseDto;
import com.sparta.springwebscheduler.entity.Schedule;
import com.sparta.springwebscheduler.entity.User;
import com.sparta.springwebscheduler.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class SchedulerController {

    private final ScheduleService scheduleService;


    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequest){
        return ResponseEntity.ok(scheduleService.createSchedule(scheduleRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDetailResponseDto> getScheduleByIdDetail(@PathVariable Long id){
        return ResponseEntity.ok(scheduleService.getScheduleByIdDedtail(id));
    }

    @GetMapping
    public List<PageResponseDto> getScheduleList(
            @RequestParam("page") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
            ){
        return scheduleService.getScheduleList(page -1,size);
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequest, HttpServletRequest req){
        User user = (User) req.getAttribute("user");
        return scheduleService.updateSchedule(id, scheduleRequest, user);
    }

    @DeleteMapping("/{id}")
    public Long deleteSchedule(@PathVariable Long id, HttpServletRequest req){
        User user = (User) req.getAttribute("user");
        return scheduleService.deleteSchedule(id, user);
    }

    @PutMapping("/{id}/{user_id}")
    public void setUserToSchedule(@PathVariable Long id, @PathVariable Long user_id, @RequestParam Long setUserId){
        scheduleService.setUserToSchedule(id, user_id,setUserId);
    }
}
