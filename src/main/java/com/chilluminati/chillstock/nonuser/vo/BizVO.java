package com.chilluminati.chillstock.nonuser.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사업체 테이블과 연결되는 VO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BizVO {

    private Integer businessId;                 // 사업체 ID (PK)

    private String businessRegistNum;           // 사업자등록번호 (UNIQUE, NOT NULL)

    private String businessName;                // 사업체 이름 (NOT NULL)

    private String businessAddress;             // 사업체 주소 (NOT NULL)

    private Integer userId;                // 유저 ID (외래키, NOT NULL)

}
