package com.chilluminati.chillstock.example.service;


import com.chilluminati.chillstock.example.dto.UserDtoExam;
import com.chilluminati.chillstock.example.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleServiceImp implements ExampleService {
    private final ExampleRepository exampleRepository;
    public void register(UserDtoExam user) {
        exampleRepository.insert(user);
    }
    public UserDtoExam getByUserId(String userId) {
        return exampleRepository.getByUserId(userId);
    }
    public List<UserDtoExam> getAllUsers() {
        return exampleRepository.getAll();
    }
    public void deleteByUserId(String userId) {
        exampleRepository.deleteByUserId(userId);
    }
}
