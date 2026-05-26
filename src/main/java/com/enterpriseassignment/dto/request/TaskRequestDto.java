package com.enterpriseassignment.dto.request;

import com.enterpriseassignment.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDto {

    @NotBlank(message = "Task title is required")
    private String title;

    private String description;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    @NotNull(message = "Assigned user id is required")
    private Long assignedUserId;

    @NotNull(message = "Project id is required")
    private Long projectId;

    private LocalDate dueDate;
}