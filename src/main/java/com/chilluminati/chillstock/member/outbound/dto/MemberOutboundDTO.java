package com.chilluminati.chillstock.member.outbound.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberOutboundDTO {
    private Integer outbound_id;
    private LocalDate outbound_date;
    private LocalDate outbound_request_date;
    private String outbound_status;
    private Integer outbound_amount;
    private Integer product_id;
    private String product_name;
    private String reject_reason_code;
}
