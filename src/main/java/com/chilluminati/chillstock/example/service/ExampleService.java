package com.chilluminati.chillstock.example.service;

import com.chilluminati.chillstock.example.dto.UserDtoExam;

import java.util.List;

public interface ExampleService {
    void register(UserDtoExam user);

    UserDtoExam getByUserId(String userId);

    List<UserDtoExam> getAllUsers();

    void deleteByUserId(String userId);
}
