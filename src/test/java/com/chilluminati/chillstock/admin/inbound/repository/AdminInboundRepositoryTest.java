package com.chilluminati.chillstock.admin.inbound.repository;

import com.chilluminati.chillstock.admin.inbound.vo.AdminInboundRequestVO;
import com.chilluminati.chillstock.admin.inbound.vo.AdminProductVO;
import com.chilluminati.chillstock.admin.inbound.vo.StockVO;
import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.config.WebClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
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
        HikariCPConfig.class,  // ← 필요하다면 추가
        WebClientConfig.class
})
@ExtendWith(SpringExtension.class)
class AdminInboundRepositoryTest {


    @Autowired
    private AdminInboundRepository adminInboundRepository;

    @Test
    @DisplayName("입고 요청 전체 조회 테스트 (대기 상태)")
    void findAll() {
        String status = "승인";
        int limit = 10;
        int offset = 0;

        List<AdminInboundRequestVO> result = adminInboundRepository.selectInboundList(status, limit, offset);

        for (AdminInboundRequestVO adminInboundRequestVO : result) {
            log.info("{}", adminInboundRequestVO);
        }

    }

    @Test
    @DisplayName("특정 상태 전체 조회 갯수 테스트")
    void findAll2() {
        int result = adminInboundRepository.countInbound("대기");

        log.info("갯수는 " + result);

    }

    @Test
    @DisplayName("입고 승인처리")
    void approveInbound() {
        List<Integer> inboundIds = List.of(2, 5);
        int i = adminInboundRepository.approveInboundList(inboundIds, 1);
        log.info(" " + i);
    }

    @Test
    @DisplayName("특정 제품 + 구역 조합의 재고 조회")
    void approveInbound2() {

        StockVO stockByProductAndArea = adminInboundRepository.findStockByProductAndArea(2, 2);

        log.info(" " + stockByProductAndArea);

    }
    @Test
    @DisplayName("재고 추가 트리거 되는지 확인 테스트")
    void approveInbound3() {
        int i = adminInboundRepository.insertStock(1, 1, 20);
        log.info("확인  " + i);
    }

    @Test
    @DisplayName("기존 재고 수량 추가시 트리거 되는 지 확인 테스트")
    void approveInbound4() {
        int i = adminInboundRepository.updateStock(1, 20);
        log.info("확인  " + i);
    }

    @Test
    @DisplayName("입고요청 조회")
    void approveInbound5() {
        AdminInboundRequestVO inboundById = adminInboundRepository.findInboundById(1);
        log.info("확인  " + inboundById);
    }
    @Test
    @DisplayName("제품 정보 조회")
    void approveInbound6() {
        AdminProductVO productById = adminInboundRepository.findProductById(1);
        log.info("확인 " + productById);
    }
    @Test
    @DisplayName("입고 요청 반려 처리")
    void approveInbound7() {
        int i = adminInboundRepository.rejectInbound(2, "공간 부족");
        log.info("결과 " + i);
    }

}