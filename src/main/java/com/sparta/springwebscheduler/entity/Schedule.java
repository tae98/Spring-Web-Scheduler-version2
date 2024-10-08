package com.sparta.springwebscheduler.entity;

import com.sparta.springwebscheduler.dto.ScheduleDto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "schedules") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Schedule extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//AUTO-INCREMENT ID VALUE
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Setter
    @Column(name = "weather", nullable= true)
    private String weather;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) // 연속성 전이로 포함한 댓글 전체 삭제
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "schedule")
    private List<Storage> StorageList = new ArrayList<>();

    public void addCommentList(Comment comment){
        this.commentList.add(comment);
        comment.setSchedule(this);
    }

    public Schedule(ScheduleRequestDto requestDto) {
        this.user_id = requestDto.getUser_id();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.user_id = requestDto.getUser_id();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
