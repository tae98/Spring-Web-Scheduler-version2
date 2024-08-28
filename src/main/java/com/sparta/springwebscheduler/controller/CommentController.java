package com.sparta.springwebscheduler.controller;

import com.sparta.springwebscheduler.dto.CommentDto.CommentRequestDto;
import com.sparta.springwebscheduler.dto.CommentDto.CommentResponseDto;
import com.sparta.springwebscheduler.entity.Comment;
import com.sparta.springwebscheduler.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{schedule_id}")
    public ResponseEntity<CommentResponseDto> postComment(@PathVariable Long schedule_id, @RequestBody CommentRequestDto commentRequest){
        return ResponseEntity.ok(commentService.postComment(schedule_id,commentRequest));
    }

    @GetMapping("/{comment_id}")
    public Comment getCommentById(@PathVariable Long comment_id){
        return commentService.getCommentById(comment_id);
    }

    @GetMapping()
    public List<Comment> getCommentList(){
        return commentService.getCommentList();
    }

    @PutMapping("/{comment_id}")
    public Comment updateComment(@PathVariable Long comment_id, @RequestBody CommentRequestDto commentRequest){
        return commentService.updateComment(comment_id, commentRequest);
    }

    @DeleteMapping("/{comment_id}")
    public Long deleteComment(@PathVariable Long comment_id){
        return commentService.deleteComment(comment_id);
    }
}
