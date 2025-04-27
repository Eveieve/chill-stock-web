package com.chilluminati.chillstock.admin.inbound.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StockVO {
    private Integer stockId;      // 재고 ID
    private Integer stockAmount;  // 재고 수량
    private Integer productId;    // 제품 ID
    private Integer areaId;       // 구역 ID
}
