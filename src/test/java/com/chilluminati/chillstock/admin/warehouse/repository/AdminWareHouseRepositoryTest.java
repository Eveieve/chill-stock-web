package com.chilluminati.chillstock.admin.warehouse.repository;

import com.chilluminati.chillstock.admin.warehouse.vo.AdminAreaSpaceRemainVo;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;
import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.config.WebClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
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
        HikariCPConfig.class,
        WebClientConfig.class// ← 필요하다면 추가
})
@ExtendWith(SpringExtension.class)
class AdminWareHouseRepositoryTest {
    @Autowired
    AdminWareHouseRepository adminWareHouseRepository;
    @Autowired
    private AdminAreaRepository adminAreaRepository;

//    @Test
//    void createWarehouse() {
//        // given
//        AdminWarehouseVo adminWarehouseVo = AdminWarehouseVo.builder()
//                .warehouseName("test")
//                .warehouseSpace(1500)
//                .warehouseAddress("address")
//                .warehouseAmount(15000)
//                .build();
//
//        // when
//        adminWareHouseRepository.createWarehouse(adminWarehouseVo);
//        AdminWarehouseVo result = adminWareHouseRepository.adminGetWarehouseById(1).get();
//
//        // then
//        Assertions.assertEquals(adminWarehouseVo.getWarehouseName(), result.getWarehouseName());
//        Assertions.assertEquals(adminWarehouseVo.getWarehouseSpace(), result.getWarehouseSpace());
//        Assertions.assertEquals(adminWarehouseVo.getWarehouseAddress(), result.getWarehouseAddress());
//        Assertions.assertEquals(adminWarehouseVo.getWarehouseAmount(), result.getWarehouseAmount());
//    }

    @Test
    void findByIdWarehouse() {
        Integer wareHouseId = 1;
        //when
        AdminWarehouseVo adminWarehouseVo = adminWareHouseRepository.adminGetWarehouseById(wareHouseId).get();
        //then
        assertNotNull(adminWarehouseVo);
    }

    @Test
    void findAllWarehouses() {
        List<AdminWarehouseVo> adminWarehouseVos = adminWareHouseRepository.adminGetAllWarehouses();
        assertNotNull(adminWarehouseVos);
        adminWarehouseVos.forEach(adminWarehouseVo -> {
            log.info(adminWarehouseVo.toString());
        });
    }

    @Test
    void findByAddress() {
        AdminWarehouseVo adminWarehouseVo = AdminWarehouseVo.builder()
                .warehouseName("test")
                .warehouseSpace(1500)
                .warehouseAddress("address")
                .warehouseAmount(15000)
                .build();

        // when
        adminWareHouseRepository.createWarehouse(adminWarehouseVo);
        List<AdminWarehouseVo> addr = adminWareHouseRepository.adminGetAllWarehouseByAddress("addr");

        assertNotNull(addr);
    }

    @Test
    void adminAreaSpaceRemainVo(){
        List<AdminAreaSpaceRemainVo> allAdminAreaSpaceUsage = adminAreaRepository.getAllAdminAreaSpaceUsage();
        assertNotNull(allAdminAreaSpaceUsage);
    }
}