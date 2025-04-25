package com.chilluminati.chillstock.member.product.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.member.product.dto.CategoryMainDTO;
import com.chilluminati.chillstock.member.product.dto.CategoryMidDTO;
import com.chilluminati.chillstock.member.product.dto.ProductDTO;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class
})
@Slf4j
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setup() {
        // 1. 테스트용 사용자 정보 생성
        EmailUserDetails userDetails = new EmailUserDetails(
                3,                          // userId
                "testuser@example.com",     // userLoginId
                "encodedPassword",          // userPassword
                "ROLE_member"               // userType
        );
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, List.of(() -> userDetails.getUserType()));

        SecurityContextHolder.getContext().setAuthentication(auth);


    }

    @Test
    void getMyPagedProducts_shouldReturnPagedResult() {
        int page = 2;
        int pageSize = 10;

        List<ProductDTO> products = productService.getMyPagedProducts(page, pageSize);

        assertThat(products).isNotNull();
        assertThat(products.size()).isLessThanOrEqualTo(pageSize);

        products.forEach(p ->
                log.info("제품 ID: , 이름: ", p.getProductId(), p.getProductName()));
    }

    @Test
    @DisplayName("특정 사용자의 제품 개수를 올바르게 조회한다")
    void countMyProducts_shouldReturnProductCountForUser() {


        // When: 서비스 메서드를 호출하여 로그인 유저의 제품 개수를 조회
        int myProductCount = productService.getMyProductCount();

        // Then: 개수가 0보다 크다는 것을 검증
        assertThat(myProductCount).isGreaterThan(0);

        log.info("내 제품 개수 = {}", myProductCount);
    }

//    @Test
//    @DisplayName("회원 제품 등록 service 부분 테스트")
//    void getMyProducts_shouldReturnProducts() {
//        // given: 더미 제품 데이터 생성
//        ProductDTO dto = ProductDTO.builder()
//                .productName("테스트 제품")
//                .productSize(5)
//                .storageTemperature(4)
//                .expirationDate(Date.valueOf("2025-12-31"))
//                .categoryMainId(1)  // 대분류는 필수는 아님, 테스트 시 참고용
//                .categoryMidId(2)   // 실제 INSERT에 사용될 중분류 ID
//                .build();
//
//        productService.registerProduct(dto);
//
//        log.info(dto.toString());
//    }

    @Test
    @DisplayName("제품 삭제 테스트")
    void product_delete() {
        productService.deleteProduct(1);

    }

    @Test
    @DisplayName("제품 수정 테스트 - 실제 ID로 테스트")
    void product_update() {
        // 예시: 실제 DB에 존재하는 product_id = 1
        ProductDTO dto = ProductDTO.builder()
                .productId(2) // 꼭 넣어야 함
                .productName("수정된 제품명")
                .productSize(15)
                .storageTemperature(3)
//                .expirationDate(LocalDate.valueOf("2026-01-01"))
                .categoryMidId(2)
                .build();

        productService.updateProduct(dto);

    }

    @Test
    @DisplayName("대분류 카테고리 전체 조회")
    void getMainCategory() {
        List<CategoryMainDTO> allMainCategories = productService.getAllMainCategories();
        assertThat(allMainCategories).isNotNull();
        for (CategoryMainDTO allMainCategory : allMainCategories) {
            log.info(allMainCategory.toString());
        }
    }
    @Test
    @DisplayName("중분류 카테고리 전체조회")
    void getMidCategory() {
        List<CategoryMidDTO> allMidCategories = productService.getAllMidCategories();
        assertThat(allMidCategories).isNotNull();
        for (CategoryMidDTO allMainCategory : allMidCategories) {
            log.info(allMainCategory.toString());
        }
    }
}


