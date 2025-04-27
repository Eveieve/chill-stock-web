package com.chilluminati.chillstock.admin.inbound.repository;

import com.chilluminati.chillstock.admin.inbound.vo.AdminInboundRequestVO;
import com.chilluminati.chillstock.admin.inbound.vo.AdminProductVO;
import com.chilluminati.chillstock.admin.inbound.vo.StockVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminInboundRepository {

    // 입고 요청 목록 조회 (상태 필터, 페이징)
    List<AdminInboundRequestVO> selectInboundList(
            @Param("status") String status,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset
    );

    // 총 개수
    int countInbound(@Param("status") String status);


    //입고 요청 승인 처리 (상태, 날짜, 관리자 ID 변경)
    int approveInboundList(@Param("inboundIds") List<Integer> inboundIds,
                           @Param("adminId") Integer adminId);

    // 특정 제품+구역 조합의 재고 조회
    StockVO findStockByProductAndArea(@Param("productId") Integer productId,
                                      @Param("areaId") Integer areaId);

    // 재고 추가 (INSERT)
    int insertStock(@Param("productId") Integer productId,
                    @Param("areaId") Integer areaId,
                    @Param("amount") Integer amount);

    // 재고 업데이트 (UPDATE)
    int updateStock(@Param("stockId") Integer stockId,
                    @Param("amount") Integer amount);

    // 입고 요청 1건 조회
    AdminInboundRequestVO findInboundById(@Param("inboundId") Integer inboundId);

    // 제품 정보 조회
    AdminProductVO findProductById(@Param("productId") Integer productId);

    //  [추가] 입고 요청 반려 처리
    int rejectInbound(@Param("inboundId") Integer inboundId,
                      @Param("rejectReasonMessage") String rejectReasonMessage);

    // userId로 adminId 조회
    Integer findAdminIdByUserId(@Param("userId") Integer userId);


}
