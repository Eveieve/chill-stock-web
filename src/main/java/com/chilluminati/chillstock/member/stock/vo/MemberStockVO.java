package com.chilluminati.chillstock.member.stock.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberStockVO {
    private Integer stock_id;
    private Integer product_id;
    private String product_name;
    private Date expiration_date;
    private Integer stock_amount;
    private LocalDate inbound_date;
}
