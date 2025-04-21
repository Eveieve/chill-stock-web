package com.chilluminati.chillstock.member.stock.repository;

import com.chilluminati.chillstock.member.stock.vo.MemberStockVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberStockRepository {
    List<MemberStockVO> readAllMemberStock(@Param("userId") Integer userId, @Param("productName") String productName);
}
