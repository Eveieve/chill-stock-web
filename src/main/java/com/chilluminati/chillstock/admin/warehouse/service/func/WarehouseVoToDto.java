package com.chilluminati.chillstock.admin.warehouse.service.func;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class WarehouseVoToDto implements
        Function<AdminWarehouseVo, AdminWarehouseDto> {
    @Override
    public AdminWarehouseDto apply(AdminWarehouseVo adminWarehouseVo) {
        return AdminWarehouseDto.builder()
                .warehouseId(adminWarehouseVo.getWarehouseId())
                .warehouseName(adminWarehouseVo.getWarehouseName())
                .warehouseSpace(adminWarehouseVo.getWarehouseSpace())
                .warehouseAddress(adminWarehouseVo.getWarehouseAddress())
                .warehouseAmount(adminWarehouseVo.getWarehouseAmount())
                .build();
    }
}
