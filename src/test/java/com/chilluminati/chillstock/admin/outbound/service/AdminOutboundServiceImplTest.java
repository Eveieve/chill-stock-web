package com.chilluminati.chillstock.admin.outbound.service;

import com.chilluminati.chillstock.admin.outbound.dto.AdminOutboundDTO;
import com.chilluminati.chillstock.admin.outbound.repository.AdminOutboundRepository;
import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.chilluminati.chillstock.admin.outbound.common.RejectCode.*;
import static com.chilluminati.chillstock.admin.outbound.common.RejectCode.RJ203;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class  // ← 필요하다면 추가
})
@ExtendWith(SpringExtension.class)
class AdminOutboundServiceImplTest {
    private final AdminOutboundService adminOutboundService;
    private final AdminOutboundRepository adminOutboundRepository;

    @Autowired
    public AdminOutboundServiceImplTest(AdminOutboundService adminOutboundService, AdminOutboundRepository adminOutboundRepository) {
        this.adminOutboundService = adminOutboundService;
        this.adminOutboundRepository = adminOutboundRepository;
    }

    @Test
    void readAllOutboundRequest() {
       // Given
        Integer page = adminOutboundRepository.count(null);
        Integer limit = 10;

        // When
        Integer offset = (page - 1) * limit;
        List<Object> result = adminOutboundRepository.readAllOutboundRequest(offset, limit).stream().map(vo -> {
            return AdminOutboundDTO.builder()
                    .outbound_id(vo.getOutbound_id())
                    .outbound_date(vo.getOutbound_date())
                    .outbound_request_date(vo.getOutbound_request_date())
                    .outbound_status(vo.getOutbound_status())
                    .outbound_amount(vo.getOutbound_amount())
                    .stock_amount(vo.getStock_amount())
                    .product_name(vo.getProduct_name())
                    .reject_reason_message(vo.getReject_reason_message())
                    .build();
        }).collect(Collectors.toList());

        // Then
        log.info(result.toString());
        assertNotNull(result);
    }

    @Test
    void readOutboundRequestByStatus() {
        // Given
        String status = "승인";
        Integer page = adminOutboundRepository.count(status);
        Integer limit = 10;

        // When
        Integer offset = (page - 1) * limit;
        List<AdminOutboundDTO> result = adminOutboundRepository.readOutboundRequestByStatus(status, offset, limit).stream().map(vo -> {
            return AdminOutboundDTO.builder()
                    .outbound_id(vo.getOutbound_id())
                    .outbound_date(vo.getOutbound_date())
                    .outbound_request_date(vo.getOutbound_request_date())
                    .outbound_status(vo.getOutbound_status())
                    .outbound_amount(vo.getOutbound_amount())
                    .stock_amount(vo.getStock_amount())
                    .product_name(vo.getProduct_name())
                    .reject_reason_message(vo.getReject_reason_message())
                    .build();
        }).collect(Collectors.toList());

        // Then
        log.info(result.toString());
        assertNotNull(result);
    }

    @Test
    void count() {
        // Given
        String status = null;

        // When
        Integer result = adminOutboundRepository.count(status);

        // Then
        log.info(result.toString());
        assertNotNull(result);
    }

    @Test
    void updateOutboundStatus() {
        Integer userId = 1;
        Integer adminId = adminOutboundRepository.getAdminId(userId);
        log.info(adminId.toString());
        assertNotNull(adminId);

        String newStatus = "승인";
        Integer outboundId = 10;
        String rejectReason = null;
        if (newStatus.equals("승인")) {
            adminOutboundRepository.updateOutboundStatus(newStatus, adminId, outboundId, rejectReason);
            List<Map<String, Integer>> stockAndOutboundAmount = adminOutboundRepository.getStockAndOutboundAmount(outboundId);
            Integer stockAmount = stockAndOutboundAmount.get(0).get("stock_amount");
            Integer outboundAmount = stockAndOutboundAmount.get(0).get("outbound_amount");
            if (stockAmount < outboundAmount) {
                throw new RuntimeException("출고수량이 재고수량보다 더 많습니다.");
            } else {
                adminOutboundRepository.updateStock(outboundId);
                if ((stockAmount - outboundAmount) == 0) {
                    adminOutboundRepository.deleteZeroStock();
                }
            }
        }
    }

    @Test
    void updateOutboundStatus1() {
        Integer userId = 1;
        Integer adminId = adminOutboundRepository.getAdminId(userId);
        log.info(adminId.toString());

        String newStatus = "반려";
        Integer outboundId = 11;
        String rejectReason = "RJ202";
        if (rejectReason.equals(RJ201.getCode())) {
            rejectReason = RJ201.getMsg();
        } else if (rejectReason.equals(RJ202.getCode())) {
            rejectReason = RJ202.getMsg();
        } else if (rejectReason.equals(RJ203.getCode())) {
            rejectReason = RJ203.getMsg();
        }
        adminOutboundRepository.updateOutboundStatus(newStatus, adminId, outboundId, rejectReason);
        log.info(rejectReason);
    }
}