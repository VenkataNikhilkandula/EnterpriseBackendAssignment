package com.enterpriseassignment.dto.request;

import com.enterpriseassignment.enums.TaskPriority;
import com.enterpriseassignment.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskUpdateDto {

    private String title;

    private String description;

    private TaskPriority priority;

    private TaskStatus status;

    private LocalDate dueDate;
}