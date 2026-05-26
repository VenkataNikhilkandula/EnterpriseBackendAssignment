package com.enterpriseassignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskActivityResponseDto {

    private Long id;

    private String activityType;

    private String oldValue;

    private String newValue;

    private String updatedBy;

    private LocalDateTime timestamp;
}