package com.chilluminati.chillstock.admin.inventory.repository;

import com.chilluminati.chillstock.admin.inventory.vo.InventoryHistoryVO;
import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class  // ← 필요하다면 추가
})
@ExtendWith(SpringExtension.class)
@Slf4j
class InventoryRepositoryTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    void selectInventoryHistoryTest() {
        // when
        List<InventoryHistoryVO> result = inventoryRepository.selectInventoryHistory();

        // then
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty(), "입출고 이력이 존재해야 합니다.");

        // 출력 로그
        result.forEach(vo -> log.info("조회된 이력: " + vo));
    }
}