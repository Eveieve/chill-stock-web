package com.chilluminati.chillstock.member.inbound.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InboundRequestDTO {
    private  Integer productId;  // 어떤 제품에 대한 입고 요청인지
    private  Integer amount;     // 수량
}
