package com.chilluminati.chillstock.admin.warehouse.vo;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminWarehouseSpaceRemainVo {
    private Integer warehouseId;
    private Integer remainSpace;
}
