package com.chilluminati.chillstock.member.product.vo;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryMidVO {
    private Integer categoryMidId;     // 중분류 ID
    private String categoryName;   // 중분류 이름
    private Integer categoryMainId;    // 연결된 대분류 ID
}
