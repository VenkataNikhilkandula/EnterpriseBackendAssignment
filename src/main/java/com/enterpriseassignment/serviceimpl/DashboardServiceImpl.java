package com.enterpriseassignment.serviceimpl;

import com.enterpriseassignment.dto.response.DashboardResponseDto;
import com.enterpriseassignment.entity.Task;
import com.enterpriseassignment.enums.TaskStatus;
import com.enterpriseassignment.exception.ResourceNotFoundException;
import com.enterpriseassignment.repository.ProjectRepository;
import com.enterpriseassignment.repository.TaskRepository;
import com.enterpriseassignment.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Override
    public DashboardResponseDto getDashboardMetrics() {

        List<Task> allTasks = taskRepository.findAll();

        long totalTasks = allTasks.size();

        long completedTasks = allTasks.stream()
                .filter(task ->
                        task.getStatus() == TaskStatus.DONE)
                .count();

        long pendingTasks = allTasks.stream()
                .filter(task ->
                        task.getStatus() != TaskStatus.DONE)
                .count();

        long blockedTasks = allTasks.stream()
                .filter(task ->
                        task.getStatus() == TaskStatus.BLOCKED)
                .count();

        long overdueTasks = allTasks.stream()
                .filter(task ->
                        task.getDueDate() != null &&
                        task.getDueDate().isBefore(LocalDate.now()) &&
                        task.getStatus() != TaskStatus.DONE)
                .count();

        DashboardResponseDto dto =
                new DashboardResponseDto();

        dto.setTotalTasks(totalTasks);
        dto.setCompletedTasks(completedTasks);
        dto.setPendingTasks(pendingTasks);
        dto.setBlockedTasks(blockedTasks);
        dto.setOverdueTasks(overdueTasks);

        return dto;
    }

    @Override
    public Double getProjectProgress(Long projectId) {

        projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found"));

        List<Task> tasks =
                taskRepository.findByProjectId(projectId);

        if (tasks.isEmpty()) {
            return 0.0;
        }

        long completedTasks = tasks.stream()
                .filter(task ->
                        task.getStatus() == TaskStatus.DONE)
                .count();

        return ((double) completedTasks / tasks.size()) * 100;
    }
}