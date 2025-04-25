package com.chilluminati.chillstock.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = {
        "com.chilluminati.chillstock.example.repository",
        "com.chilluminati.chillstock.admin.user.repository",
        "com.chilluminati.chillstock.nonuser.repository",
        "com.chilluminati.chillstock.member.mypage.repository",
        "com.chilluminati.chillstock.member.inbound.repository",
        "com.chilluminati.chillstock.member.product.repository",
        "com.chilluminati.chillstock.admin.inventory.repository",
        "com.chilluminati.chillstock.admin.warehouse.repository",
        "com.chilluminati.chillstock.member.stock.repository",
        "com.chilluminati.chillstock.member.outbound.repository"
})// 매퍼 인터페이스 위치
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*.xml")
        );
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}