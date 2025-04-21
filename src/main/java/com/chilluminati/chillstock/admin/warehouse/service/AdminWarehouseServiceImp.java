package com.chilluminati.chillstock.admin.warehouse.service;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.repository.AdminWareHouseRepository;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminWarehouseServiceImp implements AdminWarehouseService{

    private final AdminWareHouseRepository adminWareHouseRepository;

    @Override
    public void registerWarehouse(AdminWarehouseDto adminWarehouseDto) {
        AdminWarehouseVo adminWarehouseVo = AdminWarehouseVo.builder()
                .warehouseName(adminWarehouseDto.getWarehouseName())
                .warehouseSpace(adminWarehouseDto.getWarehouseSpace())
                .warehouseId(adminWarehouseDto.getWarehouseId())
                .warehouseAddress(adminWarehouseDto.getWarehouseAddress())
                .warehouseAmount(adminWarehouseDto.getWarehouseAmount())

                .build();

        adminWareHouseRepository.createWarehouse(adminWarehouseVo);
    }
}
