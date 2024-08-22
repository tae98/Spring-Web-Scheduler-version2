package com.sparta.springwebscheduler.entity;

import com.sparta.springwebscheduler.dto.CommentRequestDto;
import com.sparta.springwebscheduler.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment") // 매핑할 테이블의 이름을 지정
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//AUTO-INCREMENT ID VALUE
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.comment = requestDto.getComment();
    }


    public void update(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.comment = requestDto.getComment();
    }
}
