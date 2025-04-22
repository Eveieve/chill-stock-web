package com.chilluminati.chillstock.admin.inventory.service;

import com.chilluminati.chillstock.admin.inventory.dto.InventoryHistoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryHistoryDTO> getInventoryHistoryList();
}
