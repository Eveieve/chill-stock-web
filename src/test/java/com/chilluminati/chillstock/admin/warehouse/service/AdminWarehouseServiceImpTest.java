package com.chilluminati.chillstock.admin.warehouse.service;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminAreaWithRemainDistanceDto;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.exception.StorageConditionNotFoundException;
import com.chilluminati.chillstock.admin.warehouse.repository.AdminAreaRepository;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminStorageVo;
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
    private final AdminAreaRepository adminAreaRepository;

    @Autowired
    public AdminWarehouseServiceImpTest(AdminWarehouseService adminWarehouseService , AdminAreaRepository adminAreaRepository) {
        this.adminWarehouseService = adminWarehouseService;
        this.adminAreaRepository = adminAreaRepository;
    }

    @Test
    void findStorageIdByTemperatureTest(){
        Integer temperature = 4;
        Integer storageId = adminAreaRepository.AdminGetAllStorages().stream()
                .filter(adminStorageVo ->
                        adminStorageVo.getMinTemp() <= temperature && temperature <= adminStorageVo.getMaxTemp())
                .findFirst()
                .map(AdminStorageVo::getStorageId)
                .orElseThrow(() -> new StorageConditionNotFoundException("해당하는 온도를 만족하는 보관조건이 없습니다."));
        
        assertEquals(storageId, 3);
    }

}