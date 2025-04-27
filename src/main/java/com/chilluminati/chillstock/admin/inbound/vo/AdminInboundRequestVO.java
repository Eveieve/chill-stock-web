package com.chilluminati.chillstock.admin.inbound.vo;

import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminInboundRequestVO {
    private Integer inboundId;
    private LocalDateTime inboundDate;
    private LocalDateTime inboundRequestDate;
    private String inboundStatus;
    private Integer businessId;
    private Integer inboundAmount;
    private String productName;
    private Integer productId;
    private String rejectReasonMessage;
}
