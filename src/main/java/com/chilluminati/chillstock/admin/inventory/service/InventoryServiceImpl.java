package com.chilluminati.chillstock.admin.inventory.service;

import com.chilluminati.chillstock.admin.inventory.dto.InventoryHistoryDTO;
import com.chilluminati.chillstock.admin.inventory.repository.InventoryRepository;
import com.chilluminati.chillstock.admin.inventory.vo.InventoryHistoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryHistoryDTO> getInventoryHistoryPaged(int page, int pageSize) {
        int offset = (page - 1) * pageSize; // 시작 지점 계싼

        List<InventoryHistoryVO> voList = inventoryRepository.selectInventoryHistoryPaged(pageSize,offset);

        return voList.stream()
                .map(vo -> InventoryHistoryDTO.builder()
                        .productName(vo.getProductName())
                        .type(vo.getType())
                        .amount(vo.getAmount())
                        .currentStock(vo.getCurrentStock())
                        .warehouseName(vo.getWarehouseName())
                        .areaCode(vo.getAreaCode())
                        .handledAt(vo.getHandledAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalHistoryCount() {
        return inventoryRepository.countInventoryHistory();
    }
}
