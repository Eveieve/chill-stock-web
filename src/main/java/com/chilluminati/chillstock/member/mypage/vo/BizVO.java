package com.chilluminati.chillstock.member.mypage.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사업체 테이블과 연결되는 VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BizVO {

    private Integer businessId;                 // 사업체 ID (PK)

    private String businessRegistNum;           // 사업자등록번호 (UNIQUE, NOT NULL)

    private String businessName;                // 사업체 이름

    private String businessAddress;             // 사업체 주소 (NOT NULL)

    private String businessPost;                // 사업체 우편번호 (NOT NULL)

    private Integer userId;                // 유저 ID (외래키, NOT NULL)

}
