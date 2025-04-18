package com.chilluminati.chillstock.example.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.example.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class  // ← 필요하다면 추가
})
@ExtendWith(SpringExtension.class)
class ExampleServiceImpTest {
    @Autowired
    private ExampleServiceImp exampleService;

    @Test
    void registerTest() {
        // given
        UserDto user = UserDto.builder()
                .username("john1")
                .password("john")
                .grantUser("admin")
                .build();
        // when
        exampleService.register(user);
    }

    @Test
    void getByUserIdTest() {
        //given
        UserDto userDto = new UserDto();
        //when
        userDto = exampleService.getByUserId("john1");
        //then
        Assertions.assertNotNull(userDto);
    }
    @Test
    void getAllUsersTest() {
        //given
        List<UserDto> users;
        //when
        users = exampleService.getAllUsers();
        //then
        Assertions.assertNotNull(users);
    }
}