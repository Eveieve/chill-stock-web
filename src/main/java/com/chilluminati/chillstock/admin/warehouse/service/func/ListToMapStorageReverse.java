package com.chilluminati.chillstock.admin.warehouse.service.func;

import com.chilluminati.chillstock.admin.warehouse.vo.AdminStorageVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ListToMapStorageReverse implements Function<List<AdminStorageVo>, Map<String, Integer>> {
    @Override
    public Map<String, Integer> apply(List<AdminStorageVo> adminStorageVos) {
        return adminStorageVos.stream()
                .collect(Collectors.toMap(
                        AdminStorageVo::getStorageName,
                        AdminStorageVo::getStorageId
                ));
    }
}
