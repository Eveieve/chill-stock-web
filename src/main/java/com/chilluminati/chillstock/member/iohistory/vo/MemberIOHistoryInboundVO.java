package com.chilluminati.chillstock.member.iohistory.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberIOHistoryInboundVO {
    private Integer inbound_id;
    private LocalDateTime inbound_date;
    private LocalDateTime inbound_request_date;
    private String inbound_status;
    private Integer inbound_amount;
    private String product_name;
    private String reject_reason_message;
}
