package com.chilluminati.chillstock.member.iohistory.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberIOHistoryOutboundDTO {
    private Integer outbound_id;
    private LocalDateTime outbound_date;
    private LocalDateTime outbound_request_date;
    private String outbound_status;
    private Integer outbound_amount;
    private String product_name;
    private String reject_reason_message;
}
