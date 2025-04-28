package com.chilluminati.chillstock.admin.warehouse.repository;

import com.chilluminati.chillstock.admin.warehouse.vo.AdminAreaSpaceRemainVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminAreaVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminStorageVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseSpaceRemainVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface AdminAreaRepository {
    /**
     * 창고별 남은공간 리스트
     * @return
     */
    List<AdminWarehouseSpaceRemainVo> getAllAdminWarehouseSpaceUsage();
    Optional<AdminWarehouseSpaceRemainVo> getAdminWarehouseSpaceUsageById(Integer warehouseId);

    /**
     * 구역별 남은공간 리스트
     * @return
     */
    List<AdminAreaSpaceRemainVo> getAllAdminAreaSpaceUsage();

    /**
     * 구역 전체 리스트
     * @return
     */
    List<AdminAreaVo> AdminGetAllAreas();

    /**
     * 구역 리스트 창고 id 별
     */
    List<AdminAreaVo> AdminGetAreaById(Integer warehouseId);

    /**
     * 온도테이블 전체 리스트
     * @return
     */
    List<AdminStorageVo> AdminGetAllStorages();

    void updateStorageIdByAreaId(@Param("areaId") Integer areaId, @Param("storageId") Integer storageId);

    void createArea(AdminAreaVo adminAreaVo);

    String getBusinessAddressByInboundId(Integer inboundId);
}
