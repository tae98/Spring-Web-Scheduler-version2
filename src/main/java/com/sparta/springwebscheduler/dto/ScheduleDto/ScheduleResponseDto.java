package com.sparta.springwebscheduler.dto.ScheduleDto;


import com.sparta.springwebscheduler.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private Long user_id;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();;
        this.user_id = schedule.getUser_id();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
