package com.chilluminati.chillstock.admin.inbound.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminInboundRequestVO {
    private Integer inboundId;
    private LocalDateTime inboundDate;
    private LocalDateTime inboundRequestDate;
    private String inboundStatus;
    private Integer adminId;
    private Integer inboundAmount;
    private Integer productId;
    private String rejectReasonMessage;
}
