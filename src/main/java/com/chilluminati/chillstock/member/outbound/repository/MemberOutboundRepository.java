package com.chilluminati.chillstock.member.outbound.repository;

import com.chilluminati.chillstock.member.outbound.vo.MemberOutboundVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberOutboundRepository {
    void createOutboundRequest(@Param("productId") Integer productId, @Param("outboundAmount") Integer outboundAmount);
    List<MemberOutboundVO> readOutboundRequests(@Param("userId") Integer userId);
}
