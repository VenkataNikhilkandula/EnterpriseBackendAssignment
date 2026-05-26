package com.enterpriseassignment.serviceimpl;

import com.enterpriseassignment.dto.request.ProjectRequestDto;
import com.enterpriseassignment.dto.response.ProjectResponseDto;
import com.enterpriseassignment.entity.Project;
import com.enterpriseassignment.entity.User;
import com.enterpriseassignment.exception.ResourceNotFoundException;
import com.enterpriseassignment.repository.ProjectRepository;
import com.enterpriseassignment.repository.UserRepository;
import com.enterpriseassignment.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto requestDto) {

        log.info("Creating project: {}", requestDto.getName());

        User createdBy = userRepository.findById(
                requestDto.getCreatedBy())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        Project project = new Project();

        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());
        project.setCreatedBy(createdBy);

        Project savedProject = projectRepository.save(project);

        return modelMapper.map(savedProject,
                ProjectResponseDto.class);
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(project ->
                        modelMapper.map(project,
                                ProjectResponseDto.class))
                .toList();
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found"));

        return modelMapper.map(project,
                ProjectResponseDto.class);
    }

    @Override
    public ProjectResponseDto updateProject(Long id,
                                            ProjectRequestDto requestDto) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found"));

        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());

        Project updatedProject =
                projectRepository.save(project);

        return modelMapper.map(updatedProject,
                ProjectResponseDto.class);
    }

    @Override
    public void deleteProject(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found"));

        projectRepository.delete(project);
    }
}