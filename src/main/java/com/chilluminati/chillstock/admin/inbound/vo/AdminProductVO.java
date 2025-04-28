package com.chilluminati.chillstock.admin.inbound.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdminProductVO {
    private Integer productId;            // 제품 ID
    private String productName;            // 제품명
    private Integer productSize;           // 제품 크기
    private Integer storageTemperature;    // 보관 온도
}
