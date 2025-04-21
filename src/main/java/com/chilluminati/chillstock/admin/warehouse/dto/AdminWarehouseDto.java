package com.chilluminati.chillstock.admin.warehouse.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminWarehouseDto {
    private Integer warehouseId; // 창고 아이디

    @NotBlank(message = "창고 이름를 입력해주세요")
    private String warehouseName; // 창고 이름

    @Min(value = 100, message = "창고 크기는 최소 100 이상이여야 합니다.")
    private Integer warehouseSpace; // 창고 크기

    @NotBlank(message = "창고 주소를 입력해주세요")
    private String warehouseAddress; // 청고 주소

    private Integer warehouseAmount; // 창고 고정 지출 금액
}
