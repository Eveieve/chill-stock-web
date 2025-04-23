package com.chilluminati.chillstock.member.stock.service;

import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;

import java.util.List;

public interface MemberStockService {
    List<MemberStockDTO> readAllMemberStock(String productName);
    List<MemberStockDTO> readAllMemberStockPaging(String productName, Integer page, Integer size);
    Integer countMemberStock(String productName);
}
