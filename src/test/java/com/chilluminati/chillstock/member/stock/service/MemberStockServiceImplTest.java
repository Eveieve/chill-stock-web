package com.chilluminati.chillstock.member.stock.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class  // ← 필요하다면 추가
})
@ExtendWith(SpringExtension.class)
class MemberStockServiceImplTest {

    @Autowired
    MemberStockServiceImpl memberStockService;

    @Test
    public void testReadAllMemberStock() {
        // Given
        Integer userId = 1;
        String productName = "피자";

        // When
        List<MemberStockDTO> result = memberStockService.readAllMemberStock(userId, productName);

        // Then
        log.info(result.toString());
        assertNotNull(result);
    }

    @Test
    public void testReadAllMemberStock1() {
        // Given
        Integer userId = 1;
        String productName = null;

        // When
        List<MemberStockDTO> result = memberStockService.readAllMemberStock(userId, productName);

        // Then
        log.info(result.toString());
        assertNotNull(result);
    }
}