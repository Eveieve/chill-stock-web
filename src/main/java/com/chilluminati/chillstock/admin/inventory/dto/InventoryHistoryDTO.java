package com.chilluminati.chillstock.admin.inventory.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryHistoryDTO {
    private String productName;      // 제품명
    private String type;             // 입출고 유형: 입고 or 출고
    private Integer amount;              // 수량
    private Integer currentStock;        // 현재 재고량
    private String warehouseName;    // 창고명
    private String areaCode;         // 구역코드 (예: A-1)
    private LocalDateTime handledAt; // 처리 시각
}
