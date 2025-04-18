package com.chilluminati.chillstock.example.repository;


import com.chilluminati.chillstock.example.dto.UserDtoExam;

import java.util.List;


public interface ExampleRepository {
    void insert(UserDtoExam user);
    UserDtoExam getByUserId(String userId);
    List<UserDtoExam> getAll();
    void deleteByUserId(String userId);
}
