package com.chilluminati.chillstock.member.product.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryMidDTO {
    private Integer categoryMidId;     // 중분류 ID
    private String categoryName;   // 중분류 이름
    private Integer categoryMainId;    // 연결된 대분류 ID
}
