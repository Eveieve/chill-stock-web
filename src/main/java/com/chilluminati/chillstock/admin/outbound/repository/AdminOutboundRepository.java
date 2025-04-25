package com.chilluminati.chillstock.admin.outbound.repository;

import com.chilluminati.chillstock.admin.outbound.vo.AdminOutboundVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface AdminOutboundRepository {
    List<AdminOutboundVO> readAllOutboundRequest(@Param("offset") Integer offset, @Param("limit") Integer limit);
    List<AdminOutboundVO> readOutboundRequestByStatus(@Param("status") String status, @Param("offset") Integer offset, @Param("limit") Integer limit);
    Integer count(@Param("status") String status);
    void updateOutboundStatus(@Param("newStatus") String newStatus, @Param("adminId") Integer adminId, @Param("outboundId") Integer outboundId, @Param("msg") String msg);
    void updateStock(@Param("outboundId") Integer outboundId);
    void deleteZeroStock();
    List<Map<String, Integer>> getStockAndOutboundAmount(@Param("outboundId") Integer outboundId);
    Integer getAdminId(@Param("userId") Integer userId);
}
