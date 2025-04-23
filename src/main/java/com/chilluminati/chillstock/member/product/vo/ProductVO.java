package com.chilluminati.chillstock.member.product.vo;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVO {

    private Integer productId;
    private String productName;
    private Integer productSize;
    private Integer storageTemperature;
    private LocalDate expirationDate;

    private Integer categoryMidId;     // 중분류 ID
    private Integer userId;        // 사용자 ID → business_id 조회용

    private String categoryName;  // 카테고리 이름
    private String subCategoryName; // 중분류 이름
}
