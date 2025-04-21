package com.chilluminati.chillstock.member.outbound.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.member.outbound.dto.MemberOutboundDTO;
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
class MemberOutboundServiceImplTest {

    @Autowired MemberOutboundService memberOutboundService;

    @Test
    void createOutboundRequest() {
        // Given
        Integer productId = 1;
        Integer outboundAmount = 5;

        // When
        memberOutboundService.createOutboundRequest(productId, outboundAmount);

        // Then
    }

    @Test
    void readOutboundRequests() {
        // Given
        Integer userId = 1;

        // When
        List<MemberOutboundDTO> result = memberOutboundService.readOutboundRequests(userId);

        // Then
        log.info(result.toString());
        assertNotNull(result);
    }

    @Test
    void readAllMemberStock() {
        // Given
        Integer userId = 1;
        String productName = "피자";

        // When
        List<MemberStockDTO> result = memberOutboundService.readAllMemberStock(userId, productName);

        // Then
        log.info(result.toString());
        assertNotNull(result);
    }

    @Test
    void readAllMemberStock1() {
        // Given
        Integer userId = 1;
        String productName = null;

        // When
        List<MemberStockDTO> result = memberOutboundService.readAllMemberStock(userId, productName);

        // Then
        log.info(result.toString());
        assertNotNull(result);
    }
}