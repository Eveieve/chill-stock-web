package com.chilluminati.chillstock.admin.warehouse.service;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.repository.AdminWareHouseRepository;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;
import com.chilluminati.chillstock.common.ResultList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<AdminWarehouseDto> getAllWarehouses() {
        return adminWareHouseRepository.adminGetAllWarehouses().stream()
                .map(adminWarehouseVo -> {
                    AdminWarehouseDto adminWarehouseDto = AdminWarehouseDto.builder()
                            .warehouseId(adminWarehouseVo.getWarehouseId())
                            .warehouseName(adminWarehouseVo.getWarehouseName())
                            .warehouseSpace(adminWarehouseVo.getWarehouseSpace())
                            .warehouseAddress(adminWarehouseVo.getWarehouseAddress())
                            .warehouseAmount(adminWarehouseVo.getWarehouseAmount())
                            .build();
                    return adminWarehouseDto;
                }).collect(Collectors.toList());
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
}
