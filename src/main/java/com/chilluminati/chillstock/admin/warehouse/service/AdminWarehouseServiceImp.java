package com.chilluminati.chillstock.admin.warehouse.service;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminAreaWithRemainDistanceDto;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseRemainSpaceDto;
import com.chilluminati.chillstock.admin.warehouse.repository.AdminAreaRepository;
import com.chilluminati.chillstock.admin.warehouse.repository.AdminWareHouseRepository;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminAreaSpaceRemainVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseSpaceRemainVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;
import com.chilluminati.chillstock.common.ResultList;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminWarehouseServiceImp implements AdminWarehouseService{

    private final AdminWareHouseRepository adminWareHouseRepository;
    private final AdminAreaRepository adminAreaRepository;

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

    @Override
    public List<AdminWarehouseDto> getAllWarehouses() {
        return adminWareHouseRepository.adminGetAllWarehouses().stream()
                .map(adminWarehouseVo -> {
                    AdminWarehouseDto adminWarehouseDto = AdminWarehouseDto.builder()
                            .warehouseId(adminWarehouseVo.getWarehouseId())
                            .warehouseName(adminWarehouseVo.getWarehouseName())
                            .warehouseSpace(
                                    adminWarehouseVo.getWarehouseSpace()
                            )
                            .warehouseAddress(adminWarehouseVo.getWarehouseAddress())
                            .warehouseAmount(adminWarehouseVo.getWarehouseAmount())
                            .build();
                    return adminWarehouseDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<AdminWarehouseRemainSpaceDto> getAllWarehousesWithRemain() {
        // 1. 창고별 남은 공간 정보 1회 조회
        Map<Integer, Integer> remainSpaceMap = adminAreaRepository.getAllAdminWarehouseSpaceUsage().stream()
                .collect(Collectors.toMap(
                        AdminWarehouseSpaceRemainVo::getWarehouseId,
                        AdminWarehouseSpaceRemainVo::getRemainSpace
                ));

        // 2. 창고 목록 조회 후 DTO 변환
        return adminWareHouseRepository.adminGetAllWarehouses().stream()
                .map(adminWarehouseVo -> {
                    Integer remainSpace = remainSpaceMap.getOrDefault(adminWarehouseVo.getWarehouseId(), 0);
                    return AdminWarehouseRemainSpaceDto.builder()
                            .warehouseId(adminWarehouseVo.getWarehouseId())
                            .warehouseName(adminWarehouseVo.getWarehouseName())
                            .warehouseSpaceRemain(remainSpace) // 여기 변경됨
                            .warehouseAddress(adminWarehouseVo.getWarehouseAddress())
                            .warehouseAmount(adminWarehouseVo.getWarehouseAmount())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResultList<List<AdminWarehouseVo>> getAllWarehousesByAddress(String address) {
        List<AdminWarehouseVo> list = adminWareHouseRepository.adminGetAllWarehouseByAddress(address);

        return list.isEmpty()
                ? ResultList.emptyList("해당 주소에 일치하는 창고가 없습니다.")
                : ResultList.success(list, address + "에 대한 일치하는 창고 리스트 목록");
    }

    @Override
    public void updateWarehouse(AdminWarehouseDto adminWarehouseDto) {
        AdminWarehouseVo adminWarehouseVo = AdminWarehouseVo.builder()
                .warehouseId(adminWarehouseDto.getWarehouseId())
                .warehouseName(adminWarehouseDto.getWarehouseName())
                .warehouseSpace(adminWarehouseDto.getWarehouseSpace())
                .warehouseAddress(adminWarehouseDto.getWarehouseAddress())
                .warehouseAmount(adminWarehouseDto.getWarehouseAmount())
                .build();
        adminWareHouseRepository.adminUpdateWarehouseById(adminWarehouseVo);
    }

    /**
     * 창고 아이디로 해당 창고정보 검색
     * @param warehouseId
     * @return
     */
    @Override
    public AdminWarehouseDto getAdminWarehouseById(Integer warehouseId) {
        Optional<AdminWarehouseVo> adminWarehouseVo = adminWareHouseRepository.adminGetWarehouseById(warehouseId);
        return adminWarehouseVo
                .map(vo -> AdminWarehouseDto.builder()
                        .warehouseId(vo.getWarehouseId())
                        .warehouseName(vo.getWarehouseName())
                        .warehouseSpace(vo.getWarehouseSpace())
                        .warehouseAddress(vo.getWarehouseAddress())
                        .warehouseAmount(vo.getWarehouseAmount())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 창고 없음: " + warehouseId));
    }

    @Override
    public AdminWarehouseRemainSpaceDto getAdminWarehouseRemainSpaceById(Integer warehouseId) {
        Optional<AdminWarehouseSpaceRemainVo> adminWarehouseSpaceUsageById = adminAreaRepository.getAdminWarehouseSpaceUsageById(warehouseId);
        return adminWarehouseSpaceUsageById.map(
                vo -> AdminWarehouseRemainSpaceDto.builder()
                        .warehouseId(vo.getWarehouseId())
                        .warehouseSpaceRemain(vo.getRemainSpace())
                        .build()
        ).orElseThrow(() -> new NoSuchElementException("해당 id의 창고 없음" + warehouseId));
    }

    @Override
    public List<AdminAreaWithRemainDistanceDto> getAllAdminAreaWithRemainDistance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        Map<Integer, Integer> remainSpaceMap = adminAreaRepository.getAllAdminAreaSpaceUsage().stream()
                .collect(Collectors.toMap(
                        AdminAreaSpaceRemainVo::getAreaId,
                        AdminAreaSpaceRemainVo::getRemainSpace
                ));

        adminAreaRepository.AdminGetAllAreas().stream().map(
                vo -> AdminAreaWithRemainDistanceDto.builder()
                        .areaId(vo.getAreaId())
                        .areaSpace(vo.getAreaSpace())
                        .areaCode(vo.getAreaCode())
                        .areaPrice(vo.getAreaPrice())
                        .warehouseId(vo.getWarehouseId())
                        .storageId(vo.getStorageId())
                        .remainSpace(remainSpaceMap.get(vo.getAreaId()))
                        .distance()
                .build()
        )

    }
}
