package com.enterpriseassignment.controller;

import com.enterpriseassignment.dto.request.CommentRequestDto;
import com.enterpriseassignment.dto.response.ApiResponse;
import com.enterpriseassignment.dto.response.CommentResponseDto;
import com.enterpriseassignment.service.TaskCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskCommentController {
    @Autowired
    private TaskCommentService taskCommentService;

    @PostMapping("/{taskId}/comments")
    public ResponseEntity<ApiResponse<CommentResponseDto>> addComment(
            @PathVariable Long taskId,
            @Valid @RequestBody CommentRequestDto requestDto) {

        CommentResponseDto response =
                taskCommentService.addComment(taskId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Comment added successfully",
                        response
                ));
    }

    @GetMapping("/{taskId}/comments")
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> getCommentsByTask(
            @PathVariable Long taskId) {

        List<CommentResponseDto> comments =
                taskCommentService.getCommentsByTask(taskId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Comments fetched successfully",
                        comments
                )
        );
    }
}