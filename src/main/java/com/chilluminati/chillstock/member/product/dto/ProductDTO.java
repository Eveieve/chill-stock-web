package com.chilluminati.chillstock.member.product.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    private int productId;            // 제품 고유 ID
    private String productName;       // 제품명
    private int productSize;          // 제품 크기 (㎡)
    private int storageTemperature;   // 보관 온도
    private Date expirationDate;      // 유통 기한

    private int categoryMainId;       // 대분류 ID
    private int categoryMidId;        // 중분류 ID

    private String categoryName;      // 대분류 이름 (선택, VO → DTO 변환용)
    private String subCategoryName;   // 중분류 이름 (선택, VO → DTO 변환용)

    private Integer userId;           // 사용자 ID (Service 단에서 주입)
}
