package com.chilluminati.chillstock.member.inbound.repository;

import com.chilluminati.chillstock.member.inbound.dto.InboundRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InboundRepository {
    /**
     * 사용자의 입고 요청을 DB에 저장하는 메서드
     * @param dto 입고 요청 정보 (productId, amount, loginId 포함)
     * @return 처리된 행 수 (성공 시 1 반환)
     */
    int insertInboundRequest(InboundRequestDTO dto);
}
