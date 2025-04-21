package com.chilluminati.chillstock.member.stock.service;

import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;
import com.chilluminati.chillstock.member.stock.repository.MemberStockRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberStockServiceImpl implements MemberStockService {
    private final MemberStockRepository memberStockRepository;

    @Override
    public List<MemberStockDTO> readAllMemberStock(Integer userId, String productName) {
        return memberStockRepository.readAllMemberStock(userId, productName).stream().map(vo -> {
            return MemberStockDTO.builder()
                    .stock_id(vo.getStock_id())
                    .product_id(vo.getProduct_id())
                    .product_name(vo.getProduct_name())
                    .expiration_date(vo.getExpiration_date())
                    .stock_amount(vo.getStock_amount())
                    .inbound_date(vo.getInbound_date())
                    .build();
        }).collect(Collectors.toList());
    }
}
