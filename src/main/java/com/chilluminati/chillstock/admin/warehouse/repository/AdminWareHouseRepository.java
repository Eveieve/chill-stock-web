package com.chilluminati.chillstock.admin.warehouse.repository;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;

import java.util.List;
import java.util.Optional;

public interface AdminWareHouseRepository {
    void createWarehouse(AdminWarehouseVo adminWarehouseVo);
    Optional<AdminWarehouseVo> adminGetWarehouseById(Integer id);
    List<AdminWarehouseVo> adminGetAllWarehouses();
    List<AdminWarehouseVo> adminGetAllWarehouseByAddress(String address);
    void adminUpdateWarehouseById(AdminWarehouseVo adminWarehouseVo);
}
