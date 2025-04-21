package com.chilluminati.chillstock.member.product.vo;

import lombok.*;

import java.sql.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVO {

    private int productId;
    private String productName;
    private int productSize;
    private int storageTemperature;
    private Date expirationDate;
    private String categoryName;  // 카테고리 이름
    private String subCategoryName; // 중분류 이름
}
