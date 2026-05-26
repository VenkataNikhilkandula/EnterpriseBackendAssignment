package com.enterpriseassignment.service;

import com.enterpriseassignment.dto.request.CommentRequestDto;
import com.enterpriseassignment.dto.response.CommentResponseDto;

import java.util.List;

public interface TaskCommentService {

    CommentResponseDto addComment(
            Long taskId,
            CommentRequestDto requestDto
    );

    List<CommentResponseDto> getCommentsByTask(Long taskId);
}