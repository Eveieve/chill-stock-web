package com.chilluminati.chillstock.admin.warehouse.service.cache;


import com.chilluminati.chillstock.admin.warehouse.repository.AdminAreaRepository;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminStorageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StorageCache {

    private final AdminAreaRepository adminAreaRepository;

    private Map<Integer, AdminStorageVo> storageCache;

    @PostConstruct
    public void init() {
        List<AdminStorageVo> storageList = adminAreaRepository.AdminGetAllStorages();
        this.storageCache = storageList.stream()
                .collect(Collectors.toMap(AdminStorageVo::getStorageId, storage -> storage));
    }

    public Optional<AdminStorageVo> findSuitableStorage(Integer temperature) {
        return storageCache.values().stream()
                .filter(adminStorageVo ->
                        adminStorageVo.getMinTemp() <= temperature && temperature <= adminStorageVo.getMaxTemp())
                .findFirst();
    }
}
