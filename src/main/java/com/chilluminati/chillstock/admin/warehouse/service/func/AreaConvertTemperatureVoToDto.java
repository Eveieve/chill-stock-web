package com.chilluminati.chillstock.admin.warehouse.service.func;

import com.chilluminati.chillstock.admin.warehouse.dto.AdminAreaDto;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminAreaVo;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;

@Service
public class AreaConvertTemperatureVoToDto implements
        BiFunction<AdminAreaVo, Function<Integer,String>, AdminAreaDto> {
    @Override
    public AdminAreaDto apply(AdminAreaVo adminAreaVo, Function<Integer, String> convertTemp) {
        return AdminAreaDto.builder()
                .areaId(adminAreaVo.getAreaId())
                .areaSpace(adminAreaVo.getAreaSpace())
                .areaCode(adminAreaVo.getAreaCode())
                .areaPrice(adminAreaVo.getAreaPrice())
                .warehouseId(adminAreaVo.getWarehouseId())
                .storageMessage(convertTemp.apply(adminAreaVo.getStorageId()))
                .build();
    }
}
