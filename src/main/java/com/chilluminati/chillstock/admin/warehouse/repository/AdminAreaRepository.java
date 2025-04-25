package com.chilluminati.chillstock.admin.warehouse.repository;

import com.chilluminati.chillstock.admin.warehouse.vo.AdminAreaSpaceRemainVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminAreaVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminStorageVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseSpaceRemainVo;

import java.util.List;
import java.util.Optional;

public interface AdminAreaRepository {
    List<AdminWarehouseSpaceRemainVo> getAllAdminWarehouseSpaceUsage();
    Optional<AdminWarehouseSpaceRemainVo> getAdminWarehouseSpaceUsageById(Integer warehouseId);

    /**
     * 구역별 남은공간 리스트
     * @return
     */
    List<AdminAreaSpaceRemainVo> getAllAdminAreaSpaceUsage();

    /**
     * 구역 리스트
     * @return
     */
    List<AdminAreaVo> AdminGetAllAreas();

    List<AdminStorageVo> AdminGetAllStorages();
}
