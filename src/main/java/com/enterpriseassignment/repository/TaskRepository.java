package com.enterpriseassignment.repository;

import com.enterpriseassignment.entity.Task;
import com.enterpriseassignment.enums.TaskPriority;
import com.enterpriseassignment.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository
        extends JpaRepository<Task, Long>,
        JpaSpecificationExecutor<Task> {

    List<Task> findByProjectId(Long projectId);

    List<Task> findByAssignedUserId(Long userId);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByDueDateBefore(LocalDate date);

    List<Task> findByStatusAndPriority(
            TaskStatus status,
            TaskPriority priority
    );

    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title,
            String description
    );

    long countByStatus(TaskStatus status);

    long countByProjectId(Long projectId);

    long countByProjectIdAndStatus(
            Long projectId,
            TaskStatus status
    );

    List<Task> findByAssignedUserIdAndStatus(
            Long userId,
            TaskStatus status
    );

    List<Task> findByProjectIdAndStatus(
            Long projectId,
            TaskStatus status
    );

    List<Task> findByProjectIdAndPriority(
            Long projectId,
            TaskPriority priority
    );

    List<Task> findByAssignedUserIdAndPriority(
            Long userId,
            TaskPriority priority
    );
}