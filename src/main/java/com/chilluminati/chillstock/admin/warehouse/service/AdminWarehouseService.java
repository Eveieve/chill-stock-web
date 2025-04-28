package com.chilluminati.chillstock.admin.warehouse.service;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminAreaDto;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminAreaWithRemainDistanceDto;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseRemainSpaceDto;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminAreaSpaceRemainVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;
import com.chilluminati.chillstock.common.ResultList;

import java.util.List;

public interface AdminWarehouseService {
    void registerWarehouse(AdminWarehouseDto adminWarehouseDto);
    List<AdminWarehouseDto> getAllWarehouses();
    ResultList<List<AdminWarehouseVo>> getAllWarehousesByAddress(String address);
    void updateWarehouse(AdminWarehouseDto adminWarehouseDto);
    List<AdminWarehouseRemainSpaceDto> getAllWarehousesWithRemain();
    AdminWarehouseDto getAdminWarehouseById(Integer warehouseId);
    AdminWarehouseRemainSpaceDto getAdminWarehouseRemainSpaceById(Integer warehouseId);
    List<AdminAreaWithRemainDistanceDto> getAllAdminAreaWithRemainDistance(Integer inboundId);
    List<AdminAreaDto> getAreasByWarehouseId(Integer warehouseId);
    void updateStorageIdByAreaId(Integer areaId, Integer storageId);
    void registerAdminArea(AdminAreaDto adminAreaDto);
    Integer findStorageIdByTemperature(Integer temperature);
}
