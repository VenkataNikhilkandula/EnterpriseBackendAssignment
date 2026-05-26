package com.enterpriseassignment.mapper;

import com.enterpriseassignment.dto.request.TaskRequestDto;
import com.enterpriseassignment.dto.request.TaskUpdateDto;
import com.enterpriseassignment.dto.response.TaskResponseDto;
import com.enterpriseassignment.entity.Project;
import com.enterpriseassignment.entity.Task;
import com.enterpriseassignment.entity.User;
import com.enterpriseassignment.enums.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequestDto dto,
                         User assignedUser,
                         Project project) {

        if (dto == null) {
            return null;
        }

        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .status(TaskStatus.OPEN)
                .assignedUser(assignedUser)
                .project(project)
                .dueDate(dto.getDueDate())
                .build();
    }

    public TaskResponseDto toResponse(Task task) {

        if (task == null) {
            return null;
        }

        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .assignedUser(
                        task.getAssignedUser() != null
                                ? task.getAssignedUser().getName()
                                : null
                )
                .projectName(
                        task.getProject() != null
                                ? task.getProject().getName()
                                : null
                )
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    public void updateEntity(Task task,
                             TaskUpdateDto dto) {

        if (dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }

        if (dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }

        if (dto.getPriority() != null) {
            task.setPriority(dto.getPriority());
        }

        if (dto.getStatus() != null) {
            task.setStatus(dto.getStatus());
        }

        if (dto.getDueDate() != null) {
            task.setDueDate(dto.getDueDate());
        }
    }
}