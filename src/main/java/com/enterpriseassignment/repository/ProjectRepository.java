package com.enterpriseassignment.repository;

import com.enterpriseassignment.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository
        extends JpaRepository<Project, Long> {

    List<Project> findByCreatedById(Long userId);

    List<Project> findByNameContainingIgnoreCase(String keyword);

    boolean existsByName(String name);
}