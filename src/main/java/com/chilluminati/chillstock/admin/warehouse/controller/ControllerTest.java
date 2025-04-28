package com.chilluminati.chillstock.admin.warehouse.controller;


import com.chilluminati.chillstock.admin.warehouse.dto.AdminAreaWithRemainDistanceDto;
import com.chilluminati.chillstock.admin.warehouse.service.AdminWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControllerTest {

    private final AdminWarehouseService adminWarehouseService;

    @GetMapping("/member/test")
    public List<AdminAreaWithRemainDistanceDto> getAllAdminAreaWithRemainDistance() {
        return adminWarehouseService.getAllAdminAreaWithRemainDistance(2);
    }
}
