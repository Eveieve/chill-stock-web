package com.chilluminati.chillstock.example.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.example.dto.UserDtoExam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


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
        UserDtoExam user = UserDtoExam.builder()
                .username("john")
                .password("john")
                .grantUser("admin")
                .build();
        // when
        exampleService.register(user);
    }

    @Test
    void getByUserIdTest() {
        //given
        UserDtoExam userDto = new UserDtoExam();
        //when
        userDto = exampleService.getByUserId("john1");
        //then
        Assertions.assertNotNull(userDto);
    }
    @Test
    void getAllUsersTest() {
        //given
        List<UserDtoExam> users;
        //when
        users = exampleService.getAllUsers();
        //then
        Assertions.assertNotNull(users);
    }
}