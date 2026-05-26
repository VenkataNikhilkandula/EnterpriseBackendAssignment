package com.enterpriseassignment.controller;

import com.enterpriseassignment.dto.request.TaskRequestDto;
import com.enterpriseassignment.dto.request.TaskUpdateDto;
import com.enterpriseassignment.dto.response.ApiResponse;
import com.enterpriseassignment.dto.response.TaskResponseDto;
import com.enterpriseassignment.enums.TaskPriority;
import com.enterpriseassignment.enums.TaskStatus;
import com.enterpriseassignment.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponseDto>> createTask(
            @Valid @RequestBody TaskRequestDto requestDto) {

        TaskResponseDto response =
                taskService.createTask(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Task created successfully",
                        response
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDto>> getTaskById(
            @PathVariable Long id) {

        TaskResponseDto task =
                taskService.getTaskById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task fetched successfully",
                        task
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TaskResponseDto>>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority) {

        Page<TaskResponseDto> tasks =
                taskService.getAllTasks(page, size, status, priority);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Tasks fetched successfully",
                        tasks
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDto>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskUpdateDto requestDto) {

        TaskResponseDto updatedTask =
                taskService.updateTask(id, requestDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task updated successfully",
                        updatedTask
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTask(
            @PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task deleted successfully",
                        "Deleted"
                )
        );
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getTasksByProject(
            @PathVariable Long projectId) {

        List<TaskResponseDto> tasks =
                taskService.getTasksByProject(projectId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Project tasks fetched successfully",
                        tasks
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getTasksByUser(
            @PathVariable Long userId) {

        List<TaskResponseDto> tasks =
                taskService.getTasksByUser(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "User tasks fetched successfully",
                        tasks
                )
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getTasksByStatus(
            @PathVariable TaskStatus status) {

        List<TaskResponseDto> tasks =
                taskService.getTasksByStatus(status);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Tasks by status fetched successfully",
                        tasks
                )
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> searchTasks(
            @RequestParam String keyword) {

        List<TaskResponseDto> tasks =
                taskService.searchTasks(keyword);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Search results fetched successfully",
                        tasks
                )
        );
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<ApiResponse<TaskResponseDto>> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestParam TaskStatus status) {

        TaskResponseDto updatedTask =
                taskService.updateTaskStatus(taskId, status);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task status updated successfully",
                        updatedTask
                )
        );
    }

    @GetMapping("/overdue")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getOverdueTasks() {

        List<TaskResponseDto> tasks =
                taskService.getOverdueTasks();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Overdue tasks fetched successfully",
                        tasks
                )
        );
    }
}