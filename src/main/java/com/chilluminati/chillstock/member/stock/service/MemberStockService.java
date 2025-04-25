package com.chilluminati.chillstock.member.stock.service;

import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;

import java.util.List;

public interface MemberStockService {
    List<MemberStockDTO> readAllMemberStock(Integer page, Integer limit);
    List<MemberStockDTO> readAllMemberStockByProductName(String productName, Integer page, Integer limit);
    Integer countMemberStock(String productName);
}
