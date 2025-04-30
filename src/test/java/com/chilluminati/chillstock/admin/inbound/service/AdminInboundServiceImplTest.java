package com.chilluminati.chillstock.admin.inbound.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.config.WebClientConfig;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
class AdminInboundServiceImplTest {

    @Autowired
    private AdminInboundService adminInboundService;

    @BeforeEach
    void setup() {
        // 1. 테스트용 관리자 정보 생성 (user_id = 1번, ADMIN)
        EmailUserDetails userDetails = new EmailUserDetails(
                1,                      //  userId (1번: 관리자)
                "admin001",              //  userLoginId (user_table 기준)
                "pw1",       //
                "ADMIN"             //
        );

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        List.of(() -> userDetails.getUserType()) // ADMIN 권한 부여
                );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    @DisplayName("승인된 것들의 최적의 공간으로 들어가지는지 확인해보기")
    void test1() {

        List<Integer> inboundIds = List.of(1);

        // 입고 요청 승인 처리 실행
        adminInboundService.approveInboundRequests(inboundIds);

        // 성공적으로 실행되었는지 확인 (stock_table에 반영되었는지 수동 확인해보기
        log.info(" 입고 요청 승인 처리가 완료되었습니다.");

    }

}