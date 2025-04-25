package com.chilluminati.chillstock.admin.warehouse.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminWarehouseRemainSpaceDto {
    private Integer warehouseId; // 창고 아이디
    private String warehouseName; // 창고 이름
    private Integer warehouseSpaceRemain; // 남은 창고 크기
    private String warehouseAddress; // 청고 주소
    private Integer warehouseAmount; // 창고 고정 지출 금액
}
