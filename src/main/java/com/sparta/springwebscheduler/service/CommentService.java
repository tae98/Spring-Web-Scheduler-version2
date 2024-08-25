package com.sparta.springwebscheduler.service;

import com.sparta.springwebscheduler.dto.ConsumerDto.CommentRequestDto;
import com.sparta.springwebscheduler.dto.ConsumerDto.CommentResponseDto;
import com.sparta.springwebscheduler.entity.Comment;
import com.sparta.springwebscheduler.entity.Schedule;
import com.sparta.springwebscheduler.repository.CommentRepository;
import com.sparta.springwebscheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto postComment(Long scheduledId, CommentRequestDto commentRequest) {
        Schedule schedule = getScheduleById(scheduledId);
        // RequestDto -> Entity
        Comment comment = new Comment(commentRequest);
        //코멘트를 schedule의 commentList에 추가
        schedule.addCommentList(comment);
        //코멘트에 schedule 값 부여
        comment.setSchedule(schedule);
        // DB 저장
        Comment saveComment = commentRepository.save(comment);
        // Entity -> ResponseDto
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        return commentResponseDto;
    }
    public List<Comment> getCommentList() {
        //코멘트를 수정된 날짜 내림차순 정렬로 모두 표시
        return commentRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Comment updateComment(Long commentId, CommentRequestDto commentRequest) {
        //db에 일치하는 id comment 찾아서 객채화
        Comment comment = getCommentById(commentId);
        //해당 comment를 CommentRequestDto에 맞춰 변경
        comment.update(commentRequest);
        return comment;
    }

    public Long deleteComment(Long commentId) {
        //db에 일치하는 id 를 가지고 있는 comment 가있는지 검증
        Comment comment = getCommentById(commentId);
        //해당 comment id 를 사용 하여 삭제 (JpaRepository 내부 method)
        commentRepository.deleteById(commentId);
        return commentId;
    }

    public Comment getCommentById(Long comment_id) {
        //해당 Id 를 가진 comment 가 있는지 검증 및 반환
        return commentRepository.findById(comment_id).orElseThrow(
                ()-> new IllegalArgumentException("선택한 댓글이 존재하지 않습니다.")
        );
    }

    public Schedule getScheduleById(Long id){
        //해당 Id 를 가진 schedule 이 있는지 검증 및 반환
        return scheduleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("선택한 일정는 존재하지 않습니다.")
        );
    }


}
