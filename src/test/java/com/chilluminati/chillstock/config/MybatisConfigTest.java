package com.chilluminati.chillstock.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { HikariCPConfig.class, MybatisConfig.class })
public class MybatisConfigTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    @DisplayName("SqlSessionFactory 빈이 정상 생성되었는지 확인")
    void testSqlSessionFactory() {
        Assertions.assertNotNull(sqlSessionFactory);
    }

    @Test
    @DisplayName("SqlSessionTemplate 빈이 정상 생성되었는지 확인")
    void testSqlSessionTemplate() {
        Assertions.assertNotNull(sqlSessionTemplate);
    }
}