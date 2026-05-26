package com.enterpriseassignment.repository;

import com.enterpriseassignment.entity.TaskActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskActivityRepository
        extends JpaRepository<TaskActivity, Long> {

    List<TaskActivity> findByTaskId(Long taskId);

    List<TaskActivity> findByUpdatedById(Long userId);

    List<TaskActivity> findByActivityType(String activityType);

    long countByTaskId(Long taskId);
}