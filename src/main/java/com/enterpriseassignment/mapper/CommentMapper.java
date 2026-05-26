package com.enterpriseassignment.mapper;

import com.enterpriseassignment.dto.request.CommentRequestDto;
import com.enterpriseassignment.dto.response.CommentResponseDto;
import com.enterpriseassignment.entity.Task;
import com.enterpriseassignment.entity.TaskComment;
import com.enterpriseassignment.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public TaskComment toEntity(CommentRequestDto dto,
                                Task task,
                                User user) {

        if (dto == null) {
            return null;
        }

        return TaskComment.builder()
                .commentText(dto.getCommentText())
                .task(task)
                .user(user)
                .build();
    }

    public CommentResponseDto toResponse(
            TaskComment comment) {

        if (comment == null) {
            return null;
        }

        return CommentResponseDto.builder()
                .id(comment.getId())
                .commentText(comment.getCommentText())
                .userName(
                        comment.getUser() != null
                                ? comment.getUser().getName()
                                : null
                )
                .taskId(
                        comment.getTask() != null
                                ? comment.getTask().getId()
                                : null
                )
                .createdAt(comment.getCreatedAt())
                .build();
    }
}