package com.enterpriseassignment.serviceimpl;

import com.enterpriseassignment.dto.request.UserRequestDto;
import com.enterpriseassignment.dto.response.UserResponseDto;
import com.enterpriseassignment.entity.User;
import com.enterpriseassignment.exception.ResourceNotFoundException;
import com.enterpriseassignment.repository.UserRepository;
import com.enterpriseassignment.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {

        log.info("Creating new user with email: {}", requestDto.getEmail());

        User user = modelMapper.map(requestDto, User.class);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        log.info("Fetching all users");

        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .toList();
    }

    @Override
    public UserResponseDto getUserById(Long id) {

        log.info("Fetching user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(Long id,
                                      UserRequestDto requestDto) {

        log.info("Updating user with id: {}", id);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));

        existingUser.setName(requestDto.getName());
        existingUser.setEmail(requestDto.getEmail());
        existingUser.setRole(requestDto.getRole());

        User updatedUser = userRepository.save(existingUser);

        return modelMapper.map(updatedUser, UserResponseDto.class);
    }

    @Override
    public void deleteUser(Long id) {

        log.info("Deleting user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));

        userRepository.delete(user);
    }
}