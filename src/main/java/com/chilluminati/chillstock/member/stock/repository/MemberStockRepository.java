package com.chilluminati.chillstock.member.stock.repository;

import com.chilluminati.chillstock.member.stock.vo.MemberStockVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberStockRepository {
    List<MemberStockVO> readAllMemberStock(@Param("userId") Integer userId, @Param("productName") String productName);
    // 페이징 처리를 위한 메서드 추가
    List<MemberStockVO> readAllMemberStockPaging(@Param("userId") Integer userId, @Param("productName") String productName, @Param("offset") Integer offset, @Param("limit") Integer limit);
    Integer countMemberStock(@Param("userId") Integer userId, @Param("productName") String productName);
}
