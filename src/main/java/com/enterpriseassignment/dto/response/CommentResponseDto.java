package com.enterpriseassignment.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private Long id;

    private String commentText;

    private String userName;

    private Long taskId;

    private LocalDateTime createdAt;
}