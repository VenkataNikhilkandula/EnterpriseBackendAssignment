package com.enterpriseassignment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRequestDto {

    @NotBlank(message = "Project name is required")
    private String name;

    private String description;

    @NotNull(message = "CreatedBy user id is required")
    private Long createdBy;
}