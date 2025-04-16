package com.chilluminati.chillstock.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HikariCPConfig.class)
class HikariCPConfigTest {

    @Autowired
    HikariDataSource dataSource;

    @Test
    void getConnection() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            assertNotNull(conn); // 연결이 null이 아니어야 함
            assertFalse(conn.isClosed()); // 연결이 닫혀있지 않아야 함
            System.out.println("DB 연결 성공!");
        }
    }

    @Test
    void insertTest() throws SQLException {
        String sql = "INSERT INTO user_tbl (username, password, grantUser) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "admin");
            pstmt.setString(2, "securePassword123");
            pstmt.setString(3, "ADMIN");

            int affectedRows = pstmt.executeUpdate();

            assertEquals(1, affectedRows, "1개의 행이 삽입되어야 합니다.");
            System.out.println("데이터 삽입 성공!");
        }
    }
}