package com.chilluminati.chillstock.member.iohistory.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.member.iohistory.dto.MemberIOHistoryInboundDTO;
import com.chilluminati.chillstock.member.iohistory.dto.MemberIOHistoryOutboundDTO;
import com.chilluminati.chillstock.member.iohistory.repository.MemberIOHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
class MemberIOHistoryServiceImplTest {

    private final MemberIOHistoryService memberIOHistoryService;
    private final MemberIOHistoryRepository memberIOHistoryRepository;

    @Autowired
    public MemberIOHistoryServiceImplTest(MemberIOHistoryService memberIOHistoryService, MemberIOHistoryRepository memberIOHistoryRepository) {
        this.memberIOHistoryService = memberIOHistoryService;
        this.memberIOHistoryRepository = memberIOHistoryRepository;
    }


    @Test
    void readInboundRequests() {
        Integer userId = 3;

        List<MemberIOHistoryInboundDTO> result = memberIOHistoryRepository.readInboundRequests(userId).stream().map(vo -> {
            return MemberIOHistoryInboundDTO.builder()
                    .inbound_id(vo.getInbound_id())
                    .inbound_date(vo.getInbound_date())
                    .inbound_request_date(vo.getInbound_request_date())
                    .inbound_status(vo.getInbound_status())
                    .inbound_amount(vo.getInbound_amount())
                    .product_name(vo.getProduct_name())
                    .reject_reason_message(vo.getReject_reason_message())
                    .build();
        }).collect(Collectors.toList());

        log.info(result.toString());
        assertNotNull(result);
    }

    @Test
    void readOutboundRequestsTest() {
        Integer userId = 3;

        List<MemberIOHistoryOutboundDTO> result = memberIOHistoryRepository.readOutboundRequests(userId).stream().map(vo -> {
            return MemberIOHistoryOutboundDTO.builder()
                    .outbound_id(vo.getOutbound_id())
                    .outbound_date(vo.getOutbound_date())
                    .outbound_request_date(vo.getOutbound_request_date())
                    .outbound_status(vo.getOutbound_status())
                    .outbound_amount(vo.getOutbound_amount())
                    .product_name(vo.getProduct_name())
                    .reject_reason_message(vo.getReject_reason_message())
                    .build();
        }).collect(Collectors.toList());

        log.info(result.toString());
        assertNotNull(result);
    }
}