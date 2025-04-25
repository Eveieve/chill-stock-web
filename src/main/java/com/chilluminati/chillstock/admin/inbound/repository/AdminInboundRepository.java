//package com.chilluminati.chillstock.admin.inbound.repository;
//
//import com.chilluminati.chillstock.admin.inbound.vo.AdminInboundRequestVO;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//public interface AdminInboundRepository {
//
//    // 입고 요청 목록 조회 (상태 필터, 페이징)
//    List<AdminInboundRequestVO> selectInboundList(
//            @Param("status") String status,
//            @Param("limit") Integer limit,
//            @Param("offset") Integer offset
//    );
//
//    // 총 개수
//    int countInbound(@Param("status") String status);
//
//}
