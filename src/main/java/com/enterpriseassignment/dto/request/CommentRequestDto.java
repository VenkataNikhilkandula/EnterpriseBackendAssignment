package com.enterpriseassignment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {

    @NotNull(message = "User id is required")
    private Long userId;

    @NotBlank(message = "Comment text is required")
    private String commentText;
}