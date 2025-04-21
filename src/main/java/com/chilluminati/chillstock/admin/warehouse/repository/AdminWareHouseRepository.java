package com.chilluminati.chillstock.admin.warehouse.repository;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;

public interface AdminWareHouseRepository {
    void createWarehouse(AdminWarehouseVo adminWarehouseVo);
    AdminWarehouseVo adminGetWarehouseById(Integer id);
}
