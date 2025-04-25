package com.chilluminati.chillstock.member.iohistory.service;

import com.chilluminati.chillstock.member.iohistory.dto.MemberIOHistoryInboundDTO;
import com.chilluminati.chillstock.member.iohistory.dto.MemberIOHistoryOutboundDTO;
import com.chilluminati.chillstock.member.iohistory.repository.MemberIOHistoryRepository;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberIOHistoryServiceImpl implements MemberIOHistoryService {

    private final MemberIOHistoryRepository memberIOHistoryRepository;

    @Override
    public List<MemberIOHistoryInboundDTO> readInboundRequests() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        return memberIOHistoryRepository.readInboundRequests(userId).stream().map(vo -> {
            return MemberIOHistoryInboundDTO.builder()
                    .inbound_id(vo.getInbound_id())
                    .inbound_date(vo.getInbound_date())
                    .inbound_request_date(vo.getInbound_request_date())
                    .inbound_status(vo.getInbound_status())
                    .inbound_amount(vo.getInbound_amount())
                    .product_name(vo.getProduct_name())
                    .reject_reason_message(vo.getReject_reason_message())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<MemberIOHistoryOutboundDTO> readOutboundRequests() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        return memberIOHistoryRepository.readOutboundRequests(userId).stream().map(vo -> {
            return MemberIOHistoryOutboundDTO.builder()
                    .outbound_id(vo.getOutbound_id())
                    .outbound_date(vo.getOutbound_date())
                    .outbound_request_date(vo.getOutbound_request_date())
                    .outbound_status(vo.getOutbound_status())
                    .outbound_amount(vo.getOutbound_amount())
                    .product_name(vo.getProduct_name())
                    .reject_reason_message(vo.getReject_reason_message())
                    .build();
        }).collect(Collectors.toList());
    }
}
