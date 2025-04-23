package com.chilluminati.chillstock.member.product.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    private Integer productId;            // 제품 고유 ID
    private String productName;       // 제품명
    private Integer productSize;          // 제품 크기 (㎡)
    private Integer storageTemperature;// 보관 온도

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;      // 유통 기한

    private Integer categoryMainId;       // 대분류 ID
    private Integer categoryMidId;        // 중분류 ID

    private String categoryName;      // 대분류 이름 (선택, VO → DTO 변환용)
    private String subCategoryName;   // 중분류 이름 (선택, VO → DTO 변환용)

    private Integer userId;           // 사용자 ID (Service 단에서 주입)
}
