package com.sparta.springwebscheduler.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleDetailResponseDto {
    private Long user_id;
    private String username;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long schedule_id;
    private String title;
    private String contents;

    public ScheduleDetailResponseDto(Long user_id, String username, String email, LocalDateTime createAt, LocalDateTime modifiedAt, Long schedule_id, String title, String contents) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.schedule_id = schedule_id;
        this.title = title;
        this.contents = contents;
    }
}
