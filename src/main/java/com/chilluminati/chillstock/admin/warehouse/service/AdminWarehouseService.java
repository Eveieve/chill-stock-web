package com.chilluminati.chillstock.admin.warehouse.service;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;

import java.util.List;

public interface AdminWarehouseService {
    void registerWarehouse(AdminWarehouseDto adminWarehouseDto);
    List<AdminWarehouseDto> getAllWarehouses();
}
