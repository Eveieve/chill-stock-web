package com.chilluminati.chillstock.admin.warehouse.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminAreaWithRemainDistanceDto {
    private Integer areaId;       // 구역 ID
    private Integer areaSpace;    // 구역 면적
    private String areaCode;      // 구역 코드
    private Integer areaPrice;    // 구역 가격
    private Integer warehouseId;  // 창고 ID (FK)
    private Integer storageId;    // 보관온도 ID (nullable)
    private Integer remainSpace;
    private Integer distance;
}
