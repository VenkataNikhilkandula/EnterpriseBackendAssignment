package com.enterpriseassignment.serviceimpl;

import com.enterpriseassignment.dto.response.TaskActivityResponseDto;
import com.enterpriseassignment.entity.Task;
import com.enterpriseassignment.entity.TaskActivity;
import com.enterpriseassignment.entity.User;
import com.enterpriseassignment.exception.ResourceNotFoundException;
import com.enterpriseassignment.repository.TaskActivityRepository;
import com.enterpriseassignment.repository.TaskRepository;
import com.enterpriseassignment.repository.UserRepository;
import com.enterpriseassignment.service.TaskActivityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskActivityServiceImpl
        implements TaskActivityService {

    private final TaskActivityRepository taskActivityRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void logActivity(
            Long taskId,
            String activityType,
            String oldValue,
            String newValue,
            Long updatedBy) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found"));

        User user = userRepository.findById(updatedBy)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        TaskActivity activity = TaskActivity.builder()
                .task(task)
                .activityType(activityType)
                .oldValue(oldValue)
                .newValue(newValue)
                .updatedBy(user)
                .timestamp(LocalDateTime.now())
                .build();

        taskActivityRepository.save(activity);
    }

    @Override
    public List<TaskActivityResponseDto> getTaskActivities(
            Long taskId) {

        return taskActivityRepository.findByTaskId(taskId)
                .stream()
                .map(activity ->
                        TaskActivityResponseDto.builder()
                                .id(activity.getId())
                                .activityType(activity.getActivityType())
                                .oldValue(activity.getOldValue())
                                .newValue(activity.getNewValue())
                                .updatedBy(
                                        activity.getUpdatedBy().getName()
                                )
                                .timestamp(activity.getTimestamp())
                                .build()
                )
                .toList();
    }
}