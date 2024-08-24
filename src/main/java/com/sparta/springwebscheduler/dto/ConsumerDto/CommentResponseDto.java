package com.sparta.springwebscheduler.dto.ConsumerDto;

import com.sparta.springwebscheduler.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String username;
    private String comment;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long schedule_id;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.comment = comment.getComment();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt= comment.getModifiedAt();
        this.schedule_id = comment.getSchedule().getId();
    }
}
