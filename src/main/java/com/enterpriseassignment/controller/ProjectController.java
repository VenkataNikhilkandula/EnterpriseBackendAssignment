package com.enterpriseassignment.controller;

import com.enterpriseassignment.dto.request.ProjectRequestDto;
import com.enterpriseassignment.dto.response.ApiResponse;
import com.enterpriseassignment.dto.response.ProjectResponseDto;
import com.enterpriseassignment.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponseDto>> createProject(
            @Valid @RequestBody ProjectRequestDto requestDto) {

        ProjectResponseDto response =
                projectService.createProject(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Project created successfully",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectResponseDto>>> getAllProjects() {

        List<ProjectResponseDto> projects =
                projectService.getAllProjects();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Projects fetched successfully",
                        projects
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponseDto>> getProjectById(
            @PathVariable Long id) {

        ProjectResponseDto project =
                projectService.getProjectById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Project fetched successfully",
                        project
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponseDto>> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequestDto requestDto) {

        ProjectResponseDto updatedProject =
                projectService.updateProject(id, requestDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Project updated successfully",
                        updatedProject
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Project deleted successfully",
                        "Deleted"
                )
        );
    }
}