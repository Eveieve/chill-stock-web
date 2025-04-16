package com.chilluminati.chillstock.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariCPConfig {
    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/chillstockDB");
        config.setUsername("chillstock");
        config.setPassword("chillstock1234");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return config;
    }
    @Bean
    public HikariDataSource hikaridataSource() {
        HikariDataSource ds = new HikariDataSource(hikariConfig());
        return ds;
    }
}
