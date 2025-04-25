package com.chilluminati.chillstock.admin.inventory.service;

import com.chilluminati.chillstock.admin.inventory.dto.InventoryHistoryDTO;
import com.chilluminati.chillstock.admin.inventory.repository.InventoryRepository;
import com.chilluminati.chillstock.admin.inventory.vo.InventoryHistoryVO;
import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class  // ← 필요하다면 추가
})
@ExtendWith(SpringExtension.class)
@Slf4j
class InventoryServiceImplTest {

    @Autowired
    InventoryService inventoryService;
    @Test
    @DisplayName("입출고 이력 리스트")
    void testGetInventoryHistorypaged() {
        // given
        int page = 1;
        int pageSize = 10;
        int offset = 0;

        List<InventoryHistoryDTO> inventoryHistoryPaged = inventoryService.getInventoryHistoryPaged(1, 10);

        for (InventoryHistoryDTO inventoryHistoryDTO : inventoryHistoryPaged) {
            log.info("inventoryHistoryDTO: {}", inventoryHistoryDTO);
        }


    }
    @Test
    @DisplayName("승인된 상태 전체 갯수")
    void testGetInventoryHistorypagedCount() {
        int totalHistoryCount = inventoryService.getTotalHistoryCount();

        System.out.println(totalHistoryCount);


    }
}