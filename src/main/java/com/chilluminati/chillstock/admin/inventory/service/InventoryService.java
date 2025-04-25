package com.chilluminati.chillstock.admin.inventory.service;

import com.chilluminati.chillstock.admin.inventory.dto.InventoryHistoryDTO;

import java.util.List;

public interface InventoryService {
    // 페이징된 입출고 이력 리스트
    List<InventoryHistoryDTO> getInventoryHistoryPaged(int page, int pageSize);

    // 전체 이력 건수
    int getTotalHistoryCount();
}
