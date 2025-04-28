package com.chilluminati.chillstock.admin.inventory.repository;

import com.chilluminati.chillstock.admin.inventory.vo.InventoryHistoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface InventoryRepository {


    List<InventoryHistoryVO> selectInventoryHistoryPaged(@Param("limit") Integer limit, @Param("offset") Integer offset);


    // 전체 이력 개수 조회
    int countInventoryHistory();
}
