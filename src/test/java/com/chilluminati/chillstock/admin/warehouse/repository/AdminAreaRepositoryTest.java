package com.chilluminati.chillstock.admin.warehouse.repository;

import com.chilluminati.chillstock.admin.warehouse.vo.AdminAreaVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminStorageVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseSpaceRemainVo;
import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.config.WebClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class,  // ← 필요하다면 추가
        WebClientConfig.class
})
@ExtendWith(SpringExtension.class)
class AdminAreaRepositoryTest {
    @Autowired
    AdminAreaRepository adminAreaRepository;

    //남은공간 테스트
    @Test
    void getAllAdminWarehouseSpaceUsage() {
        adminAreaRepository.getAllAdminWarehouseSpaceUsage();
        log.info(adminAreaRepository.getAllAdminWarehouseSpaceUsage().toString());
    }

    @Test
    void getAdminWarehouseSpaceUsageById(){
        Optional<AdminWarehouseSpaceRemainVo> adminWarehouseSpaceUsageById = adminAreaRepository.getAdminWarehouseSpaceUsageById(1);
        assertTrue(adminWarehouseSpaceUsageById.isPresent());
    }

    @Test
    void getAllArea(){
        List<AdminAreaVo> adminAreaVos = adminAreaRepository.AdminGetAllAreas();
        assertNotNull(adminAreaVos);
    }

    @Test
    void getALlStorages(){
        List<AdminStorageVo> adminStorageVos = adminAreaRepository.AdminGetAllStorages();
        assertNotNull(adminStorageVos);
    }

    @Test
    void getBusinessAddress(){
        String businessAddressByInboundId = adminAreaRepository.getBusinessAddressByInboundId(2);
        assertNotNull(businessAddressByInboundId);
        log.info(businessAddressByInboundId);
    }
}