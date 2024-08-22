package com.sparta.springwebscheduler.service;

import com.sparta.springwebscheduler.dto.CommentRequestDto;
import com.sparta.springwebscheduler.dto.CommentResponseDto;
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
        //코멘트에 schedule 값 부여
        comment.setSchedule(schedule);
        // DB 저장
        Comment saveComment = commentRepository.save(comment);
        // Entity -> ResponseDto
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        return commentResponseDto;
    }
    public List<Comment> getCommentList() {
        return commentRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Comment updateComment(Long commentId, CommentRequestDto commentRequest) {
        Comment comment = getCommentById(commentId);
        comment.update(commentRequest);
        return comment;
    }

    public Long deleteComment(Long commentId) {
        Comment comment = getCommentById(commentId);
        commentRepository.deleteById(commentId);
        return commentId;
    }

    public Comment getCommentById(Long comment_id) {
        return commentRepository.findById(comment_id).orElseThrow(
                ()-> new IllegalArgumentException("선택한 댓글이 존재하지 않습니다.")
        );
    }

    public Schedule getScheduleById(Long id){
        return scheduleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }


}
