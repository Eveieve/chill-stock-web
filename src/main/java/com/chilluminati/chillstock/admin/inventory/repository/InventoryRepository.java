package com.chilluminati.chillstock.admin.inventory.repository;

import com.chilluminati.chillstock.admin.inventory.vo.InventoryHistoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface InventoryRepository {
    List<InventoryHistoryVO> selectInventoryHistory();
}
