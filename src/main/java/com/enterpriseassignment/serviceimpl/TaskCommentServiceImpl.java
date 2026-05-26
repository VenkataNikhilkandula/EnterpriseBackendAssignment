package com.enterpriseassignment.serviceimpl;

import com.enterpriseassignment.dto.request.CommentRequestDto;
import com.enterpriseassignment.dto.response.CommentResponseDto;
import com.enterpriseassignment.entity.Task;
import com.enterpriseassignment.entity.TaskComment;
import com.enterpriseassignment.entity.User;
import com.enterpriseassignment.exception.ResourceNotFoundException;
import com.enterpriseassignment.repository.TaskCommentRepository;
import com.enterpriseassignment.repository.TaskRepository;
import com.enterpriseassignment.repository.UserRepository;
import com.enterpriseassignment.service.TaskCommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskCommentServiceImpl
        implements TaskCommentService {

    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentResponseDto addComment(
            Long taskId,
            CommentRequestDto requestDto) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found"));

        User user = userRepository.findById(
                requestDto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        TaskComment comment = new TaskComment();

        comment.setTask(task);
        comment.setUser(user);
        comment.setCommentText(requestDto.getCommentText());

        TaskComment savedComment =
                taskCommentRepository.save(comment);

        return modelMapper.map(savedComment,
                CommentResponseDto.class);
    }

    @Override
    public List<CommentResponseDto> getCommentsByTask(
            Long taskId) {

        return taskCommentRepository.findByTaskId(taskId)
                .stream()
                .map(comment ->
                        modelMapper.map(comment,
                                CommentResponseDto.class))
                .toList();
    }
}