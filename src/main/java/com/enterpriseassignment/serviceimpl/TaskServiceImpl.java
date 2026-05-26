package com.enterpriseassignment.serviceimpl;

import com.enterpriseassignment.dto.request.TaskRequestDto;
import com.enterpriseassignment.dto.request.TaskUpdateDto;
import com.enterpriseassignment.dto.response.TaskResponseDto;
import com.enterpriseassignment.entity.Project;
import com.enterpriseassignment.entity.Task;
import com.enterpriseassignment.entity.User;
import com.enterpriseassignment.enums.TaskPriority;
import com.enterpriseassignment.enums.TaskStatus;
import com.enterpriseassignment.exception.ResourceNotFoundException;
import com.enterpriseassignment.repository.ProjectRepository;
import com.enterpriseassignment.repository.TaskRepository;
import com.enterpriseassignment.repository.UserRepository;
import com.enterpriseassignment.service.TaskActivityService;
import com.enterpriseassignment.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskActivityService taskActivityService;
    private final ModelMapper modelMapper;

    @Override
    public TaskResponseDto createTask(TaskRequestDto requestDto) {

        User assignedUser = userRepository.findById(
                requestDto.getAssignedUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Assigned user not found"));

        Project project = projectRepository.findById(
                requestDto.getProjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found"));

        Task task = new Task();

        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setPriority(requestDto.getPriority());
        task.setStatus(TaskStatus.OPEN);
        task.setAssignedUser(assignedUser);
        task.setProject(project);
        task.setDueDate(requestDto.getDueDate());

        Task savedTask = taskRepository.save(task);

        return modelMapper.map(savedTask,
                TaskResponseDto.class);
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found"));

        return modelMapper.map(task,
                TaskResponseDto.class);
    }

    @Override
    public Page<TaskResponseDto> getAllTasks(
            int page,
            int size,
            TaskStatus status,
            TaskPriority priority) {

        Pageable pageable =
                PageRequest.of(page, size,
                        Sort.by("id").descending());

        Page<Task> tasks = taskRepository.findAll(pageable);

        return tasks.map(task ->
                modelMapper.map(task,
                        TaskResponseDto.class));
    }

    @Override
    public TaskResponseDto updateTask(
            Long id,
            TaskUpdateDto requestDto) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found"));

        String oldStatus = task.getStatus().name();

        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setPriority(requestDto.getPriority());
        task.setStatus(requestDto.getStatus());
        task.setDueDate(requestDto.getDueDate());

        Task updatedTask = taskRepository.save(task);

        taskActivityService.logActivity(
                task.getId(),
                "STATUS_UPDATED",
                oldStatus,
                updatedTask.getStatus().name(),
                updatedTask.getAssignedUser().getId()
        );

        return modelMapper.map(updatedTask,
                TaskResponseDto.class);
    }

    @Override
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found"));

        taskRepository.delete(task);
    }

    @Override
    public List<TaskResponseDto> getTasksByProject(Long projectId) {

        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(task ->
                        modelMapper.map(task,
                                TaskResponseDto.class))
                .toList();
    }

    @Override
    public List<TaskResponseDto> getTasksByUser(Long userId) {

        return taskRepository.findByAssignedUserId(userId)
                .stream()
                .map(task ->
                        modelMapper.map(task,
                                TaskResponseDto.class))
                .toList();
    }

    @Override
    public List<TaskResponseDto> getTasksByStatus(TaskStatus status) {

        return taskRepository.findByStatus(status)
                .stream()
                .map(task ->
                        modelMapper.map(task,
                                TaskResponseDto.class))
                .toList();
    }

    @Override
    public List<TaskResponseDto> searchTasks(String keyword) {

        return taskRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        keyword,
                        keyword)
                .stream()
                .map(task ->
                        modelMapper.map(task,
                                TaskResponseDto.class))
                .toList();
    }

    @Override
    public TaskResponseDto updateTaskStatus(
            Long taskId,
            TaskStatus status) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found"));

        String oldStatus = task.getStatus().name();

        task.setStatus(status);

        Task updatedTask = taskRepository.save(task);

        taskActivityService.logActivity(
                taskId,
                "STATUS_CHANGED",
                oldStatus,
                status.name(),
                task.getAssignedUser().getId()
        );

        return modelMapper.map(updatedTask,
                TaskResponseDto.class);
    }

    @Override
    public List<TaskResponseDto> getOverdueTasks() {

        return taskRepository
                .findByDueDateBefore(LocalDate.now())
                .stream()
                .filter(task ->
                        task.getStatus() != TaskStatus.DONE)
                .map(task ->
                        modelMapper.map(task,
                                TaskResponseDto.class))
                .toList();
    }
}