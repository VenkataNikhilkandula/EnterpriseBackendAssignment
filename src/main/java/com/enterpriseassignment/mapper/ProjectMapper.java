package com.enterpriseassignment.mapper;

import com.enterpriseassignment.dto.request.ProjectRequestDto;
import com.enterpriseassignment.dto.response.ProjectResponseDto;
import com.enterpriseassignment.entity.Project;
import com.enterpriseassignment.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequestDto dto,
                            User createdBy) {

        if (dto == null) {
            return null;
        }

        return Project.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .createdBy(createdBy)
                .build();
    }

    public ProjectResponseDto toResponse(Project project) {

        if (project == null) {
            return null;
        }

        return ProjectResponseDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .createdBy(
                        project.getCreatedBy() != null
                                ? project.getCreatedBy().getName()
                                : null
                )
                .createdAt(project.getCreatedAt())
                .build();
    }

    public void updateEntity(Project project,
                             ProjectRequestDto dto) {

        if (dto.getName() != null) {
            project.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            project.setDescription(dto.getDescription());
        }
    }
}