package com.chilluminati.chillstock.member.stock.service;

import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;
import com.chilluminati.chillstock.member.stock.repository.MemberStockRepository;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberStockServiceImpl implements MemberStockService {
    private final MemberStockRepository memberStockRepository;

    @Override
    public List<MemberStockDTO> readAllMemberStock(Integer page, Integer limit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        Integer offset = (page - 1) * limit;
        return memberStockRepository.readAllMemberStock(userId, offset, limit).stream().map(vo -> {
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

    @Override
    public List<MemberStockDTO> readAllMemberStockByProductName(String productName, Integer page, Integer limit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        Integer offset = (page - 1) * limit;
        return memberStockRepository.readAllMemberStockByProductName(userId, productName, offset, limit)
                .stream().map(vo -> MemberStockDTO.builder()
                        .stock_id(vo.getStock_id())
                        .product_id(vo.getProduct_id())
                        .product_name(vo.getProduct_name())
                        .expiration_date(vo.getExpiration_date())
                        .stock_amount(vo.getStock_amount())
                        .inbound_date(vo.getInbound_date())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Integer countMemberStock(String productName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        return memberStockRepository.countMemberStock(userId, productName);
    }
}
