package com.chilluminati.chillstock.admin.warehouse.service;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.config.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class AdminWarehouseServiceImpTest {

    private final AdminWarehouseService adminWarehouseService;

    @Autowired
    public AdminWarehouseServiceImpTest(AdminWarehouseService adminWarehouseService) {
        this.adminWarehouseService = adminWarehouseService;
    }

//    @Test
//    void getAllWarehouses() {
//        List<AdminWarehouseDto> allWarehouses = adminWarehouseService.getAllWarehouses();
//        assertNotNull(adminWarehouseService.getAllWarehouses());
//        allWarehouses.forEach(adminWarehouseDto -> {
//            log.info(adminWarehouseDto.toString());
//        });
//    }
}