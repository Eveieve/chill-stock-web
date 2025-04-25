package com.chilluminati.chillstock.member.outbound.service;

import com.chilluminati.chillstock.member.outbound.dto.MemberOutboundDTO;
import com.chilluminati.chillstock.member.outbound.repository.MemberOutboundRepository;
import com.chilluminati.chillstock.member.outbound.vo.MemberOutboundVO;
import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;
import com.chilluminati.chillstock.member.stock.repository.MemberStockRepository;
import com.chilluminati.chillstock.member.stock.service.MemberStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberOutboundServiceImpl implements MemberOutboundService {
    private final MemberOutboundRepository memberOutboundRepository;
    private final MemberStockService memberStockService;

    @Override
    public void createOutboundRequest(Integer productId, Integer outboundAmount) {
        memberOutboundRepository.createOutboundRequest(productId, outboundAmount);
    }

    @Override
    public List<MemberOutboundDTO> readOutboundRequests(Integer userId) { //파라미터 삭제
        return memberOutboundRepository.readOutboundRequests(userId).stream().map(vo -> {
            return MemberOutboundDTO.builder()
                    .outbound_id(vo.getOutbound_id())
                    .outbound_date(vo.getOutbound_date())
                    .outbound_request_date(vo.getOutbound_request_date())
                    .outbound_status(vo.getOutbound_status())
                    .outbound_amount(vo.getOutbound_amount())
                    .product_id(vo.getProduct_id())
                    .product_name(vo.getProduct_name())
                    .reject_reason_code(vo.getReject_reason_code())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<MemberStockDTO> readAllMemberStock(String productName, Integer page, Integer limit) {
        return memberStockService.readAllMemberStockByProductName(productName, page, limit);
    }

    @Override
    public List<MemberStockDTO> readAllMemberStockByProductName(String productName, Integer page, Integer limit) {
        return memberStockService.readAllMemberStockByProductName(productName, page, limit);
    }

    @Override
    public Integer countMemberStock(String productName) {
        return memberStockService.countMemberStock(productName);
    }
}
