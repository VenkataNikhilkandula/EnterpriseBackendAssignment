package com.enterpriseassignment.service;

import com.enterpriseassignment.dto.request.TaskRequestDto;
import com.enterpriseassignment.dto.request.TaskUpdateDto;
import com.enterpriseassignment.dto.response.TaskResponseDto;
import com.enterpriseassignment.enums.TaskPriority;
import com.enterpriseassignment.enums.TaskStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto requestDto);

    TaskResponseDto getTaskById(Long id);

    Page<TaskResponseDto> getAllTasks(
            int page,
            int size,
            TaskStatus status,
            TaskPriority priority
    );

    TaskResponseDto updateTask(
            Long id,
            TaskUpdateDto requestDto
    );

    void deleteTask(Long id);

    List<TaskResponseDto> getTasksByProject(Long projectId);

    List<TaskResponseDto> getTasksByUser(Long userId);

    List<TaskResponseDto> getTasksByStatus(TaskStatus status);

    List<TaskResponseDto> searchTasks(String keyword);

    TaskResponseDto updateTaskStatus(
            Long taskId,
            TaskStatus status
    );

    List<TaskResponseDto> getOverdueTasks();
}