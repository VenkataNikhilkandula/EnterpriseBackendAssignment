package com.enterpriseassignment.repository;

import com.enterpriseassignment.entity.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCommentRepository
        extends JpaRepository<TaskComment, Long> {

    List<TaskComment> findByTaskId(Long taskId);

    List<TaskComment> findByUserId(Long userId);

    long countByTaskId(Long taskId);
}