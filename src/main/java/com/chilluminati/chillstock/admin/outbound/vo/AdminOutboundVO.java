package com.chilluminati.chillstock.admin.outbound.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminOutboundVO {
    private Integer outbound_id;
    private LocalDateTime outbound_date;
    private LocalDateTime outbound_request_date;
    private String outbound_status;
    private Integer outbound_amount;
    private String product_name;
    private String reject_reason_message;
}
