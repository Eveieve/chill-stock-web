package com.chilluminati.chillstock.member.outbound.service;


import com.chilluminati.chillstock.member.outbound.dto.MemberOutboundDTO;
import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;

import java.util.List;

public interface MemberOutboundService {
    void createOutboundRequest(Integer productId, Integer outboundAmount);
    List<MemberOutboundDTO> readOutboundRequests(Integer userId);
    List<MemberStockDTO> readAllMemberStock(String productName, Integer page, Integer limit);
    List<MemberStockDTO> readAllMemberStockByProductName(String productName, Integer page, Integer limit);
    Integer countMemberStock(String productName);
}
