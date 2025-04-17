package com.chilluminati.chillstock.example.repository;


import com.chilluminati.chillstock.example.dto.UserDto;

import java.util.List;


public interface ExampleRepository {
    void insert(UserDto user);
    UserDto getByUserId(String userId);
    List<UserDto> getAll();
    void deleteByUserId(String userId);
}
