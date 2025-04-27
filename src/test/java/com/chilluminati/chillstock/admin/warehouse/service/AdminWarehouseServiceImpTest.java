package com.chilluminati.chillstock.admin.warehouse.service;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminAreaWithRemainDistanceDto;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.config.WebClientConfig;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class,
        WebClientConfig.class// ← 필요하다면 추가
})
@ExtendWith(SpringExtension.class)
class AdminWarehouseServiceImpTest {

    private final AdminWarehouseService adminWarehouseService;

    @Autowired
    public AdminWarehouseServiceImpTest(AdminWarehouseService adminWarehouseService) {
        this.adminWarehouseService = adminWarehouseService;
    }

}