package com.chilluminati.chillstock.member.inbound.service;

import com.chilluminati.chillstock.member.inbound.dto.InboundRequestDTO;

public interface  InboundService {

    /**
     * 로그인한 사용자의 정보를 기반으로 입고 요청 등록
     * @param dto 클라이언트로부터 받은 입고 요청 정보 (productId, amount)
     */
    void registerInboundRequest(InboundRequestDTO dto);
}
