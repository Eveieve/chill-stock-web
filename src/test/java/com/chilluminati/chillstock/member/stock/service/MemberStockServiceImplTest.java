package com.chilluminati.chillstock.member.stock.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;
import com.chilluminati.chillstock.member.stock.repository.MemberStockRepository;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class  // ← 필요하다면 추가
})
@ExtendWith(SpringExtension.class)
class MemberStockServiceImplTest {

    private final MemberStockService memberStockService;
    private final MemberStockRepository memberStockRepository;

    @Autowired
    public MemberStockServiceImplTest(MemberStockService memberStockService, MemberStockRepository memberStockRepository) {
        this.memberStockService = memberStockService;
        this.memberStockRepository = memberStockRepository;
    }

    @Test
    public void testReadAllMemberStock() {
        // Given
        Integer userId = 3;
        Integer page = 1;
        Integer limit = 10;

        // When
        List<MemberStockDTO> result = memberStockRepository.readAllMemberStock(userId, page, limit).stream().map(vo -> {
            return MemberStockDTO.builder()
                    .stock_id(vo.getStock_id())
                    .product_id(vo.getProduct_id())
                    .product_name(vo.getProduct_name())
                    .expiration_date(vo.getExpiration_date())
                    .stock_amount(vo.getStock_amount())
                    .inbound_date(vo.getInbound_date())
                    .build();
        }).collect(Collectors.toList());

        // Then
        log.info(result.toString());
        assertNotNull(result);
    }

    @Test
    void testReadAllMemberStockByProductName() {
        // Given
        Integer userId = 3;
        String productName = "망고";
        Integer page = 1;
        Integer limit = 10;

        // When
        Integer offset = (page - 1) * limit;
        List<MemberStockDTO> result = memberStockRepository.readAllMemberStockByProductName(userId, productName, offset, limit)
                .stream().map(vo -> MemberStockDTO.builder()
                        .stock_id(vo.getStock_id())
                        .product_id(vo.getProduct_id())
                        .product_name(vo.getProduct_name())
                        .expiration_date(vo.getExpiration_date())
                        .stock_amount(vo.getStock_amount())
                        .inbound_date(vo.getInbound_date())
                        .build())
                .collect(Collectors.toList());

        // Then
        log.info(result.toString());
        assertNotNull(result);
    }
}