package com.chilluminati.chillstock.member.iohistory.repository;

import com.chilluminati.chillstock.member.iohistory.vo.MemberIOHistoryInboundVO;
import com.chilluminati.chillstock.member.iohistory.vo.MemberIOHistoryOutboundVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberIOHistoryRepository {
    List<MemberIOHistoryInboundVO> readInboundRequests(@Param("userId") Integer userId);
    List<MemberIOHistoryOutboundVO> readOutboundRequests(@Param("userId") Integer userId);
}
