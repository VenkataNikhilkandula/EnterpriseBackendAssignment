package com.enterpriseassignment.service;

import com.enterpriseassignment.dto.request.ProjectRequestDto;
import com.enterpriseassignment.dto.response.ProjectResponseDto;

import java.util.List;

public interface ProjectService {

    ProjectResponseDto createProject(ProjectRequestDto requestDto);

    List<ProjectResponseDto> getAllProjects();

    ProjectResponseDto getProjectById(Long id);

    ProjectResponseDto updateProject(Long id,
                                     ProjectRequestDto requestDto);

    void deleteProject(Long id);
}