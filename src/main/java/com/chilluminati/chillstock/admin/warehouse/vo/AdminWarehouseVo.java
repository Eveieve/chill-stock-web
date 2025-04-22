package com.chilluminati.chillstock.admin.warehouse.vo;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminWarehouseVo {
    private Integer warehouseId; // 창고 아이디
    private String warehouseName; // 창고 이름
    private Integer warehouseSpace; // 창고 크기
    private String warehouseAddress; // 청고 주소
    private Integer warehouseAmount; // 창고 고정 지출 금액
}
