package com.chilluminati.chillstock.member.inbound.service;

import com.chilluminati.chillstock.member.inbound.dto.InboundRequestDTO;
import com.chilluminati.chillstock.member.inbound.repository.InboundRepository;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InboundServiceImpl implements InboundService{

    private final InboundRepository inboundRepository;
    @Override
    public void registerInboundRequest(InboundRequestDTO dto) {

        inboundRepository.insertInboundRequest(dto);
    }
}
