package com.enterpriseassignment.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponseDto {

    private Long id;

    private String name;

    private String description;

    private String createdBy;

    private LocalDateTime createdAt;
}