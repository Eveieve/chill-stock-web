package com.chilluminati.chillstock.admin.inbound.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminInboundRequestDTO {
    private Integer inboundId;
    private LocalDateTime inboundRequestDate;
    private String inboundStatus;
    private Integer inboundAmount;

    private String productName;
    private Integer businessId;

    private String rejectReasonMessage;

}
