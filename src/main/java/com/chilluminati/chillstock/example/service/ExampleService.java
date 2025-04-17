package com.chilluminati.chillstock.example.service;

import com.chilluminati.chillstock.example.dto.UserDto;

import java.util.List;

public interface ExampleService {
    void register(UserDto user);

    UserDto getByUserId(String userId);

    List<UserDto> getAllUsers();

    void deleteByUserId(String userId);
}
