package com.chilluminati.chillstock.admin.inventory.repository;

import com.chilluminati.chillstock.admin.inventory.vo.InventoryHistoryVO;

import java.util.List;

public interface InventoryRepository {
    List<InventoryHistoryVO> selectInventoryHistory();
}
