package com.chilluminati.chillstock.admin.inventory.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryHistoryVO {
    private String productName;      // 제품명
    private String type;             // 입고/출고
    private int amount;              // 수량
    private int currentStock;        // 현재 재고량
    private String warehouseName;    // 창고명
    private String areaCode;         // 구역 코드
    private LocalDateTime handledAt; // 처리일시
}
