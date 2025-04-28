package com.chilluminati.chillstock.member.inbound.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.config.WebClientConfig;
import com.chilluminati.chillstock.member.inbound.dto.InboundRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class,  // ← 필요하다면 추가
        WebClientConfig.class
})
@ExtendWith(SpringExtension.class)
@Slf4j
class InboundServiceImplTest {


    @Autowired
    private InboundService inboundService;

    @Test
    void registerInboundRequest(){
        // given
        InboundRequestDTO dto = InboundRequestDTO.builder()
                .productId(2)
                .amount(10)
                .build();

        // when
        inboundService.registerInboundRequest(dto);

        // then
        log.info("insert successful: {}", dto);

    }
}

