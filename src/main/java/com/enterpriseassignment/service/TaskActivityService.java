package com.enterpriseassignment.service;

import com.enterpriseassignment.dto.response.TaskActivityResponseDto;

import java.util.List;

public interface TaskActivityService {

    void logActivity(
            Long taskId,
            String activityType,
            String oldValue,
            String newValue,
            Long updatedBy
    );

    List<TaskActivityResponseDto> getTaskActivities(Long taskId);
}