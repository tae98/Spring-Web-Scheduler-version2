package com.sparta.springwebscheduler.dto.ScheduleDto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long user_id;
    private String title;
    private String contents;
}
