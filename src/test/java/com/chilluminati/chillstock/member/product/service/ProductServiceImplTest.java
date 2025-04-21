package com.chilluminati.chillstock.member.product.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.member.product.dto.ProductDTO;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
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


    @Test
    void getMyProducts_shouldReturnListOfMyProducts() {


        Integer fakeUserId = 3;  // 존재하는 유저 ID로 테스트할 것 테스트통과 시큐리티적용안하고 테스트
  
        // when   
//        List<ProductDTO> result = productService.getMyProducts(fakeUserId);
//
//        // then
//        assertThat(result).isNotNull();
//        assertThat(result).isNotEmpty();
//        result.forEach(p -> log.info("조회된 제품: {}", p));
    }
    }



