package com.enterpriseassignment.dto.response;

import com.enterpriseassignment.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;

    private String name;

    private String email;

    private UserRole role;

    private LocalDateTime createdAt;
}