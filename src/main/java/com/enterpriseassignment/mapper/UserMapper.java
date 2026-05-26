package com.enterpriseassignment.mapper;

import com.enterpriseassignment.dto.request.UserRequestDto;
import com.enterpriseassignment.dto.response.UserResponseDto;
import com.enterpriseassignment.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto) {

        if (dto == null) {
            return null;
        }

        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();
    }

    public UserResponseDto toResponse(User user) {

        if (user == null) {
            return null;
        }

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public void updateEntity(User user,
                             UserRequestDto dto) {

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
    }
}