package com.chilluminati.chillstock.admin.warehouse.service.func;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class WarehouseDtoToVo implements
        Function<AdminWarehouseDto, AdminWarehouseVo> {
    @Override
    public AdminWarehouseVo apply(AdminWarehouseDto adminWarehouseDto) {
        return AdminWarehouseVo.builder()
                .warehouseName(adminWarehouseDto.getWarehouseName())
                .warehouseSpace(adminWarehouseDto.getWarehouseSpace())
                .warehouseId(adminWarehouseDto.getWarehouseId())
                .warehouseAddress(adminWarehouseDto.getWarehouseAddress())
                .warehouseAmount(adminWarehouseDto.getWarehouseAmount())
                .build();
    }
}
