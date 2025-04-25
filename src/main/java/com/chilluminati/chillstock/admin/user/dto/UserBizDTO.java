package com.chilluminati.chillstock.admin.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 회원+사업자 정보를 조회할때 쓰는 디티오
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBizDTO {
    // 회원 정보
    private String userLoginId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userType;
    private String userStatus;
    private String userRequestedAt;
    private String userApprovedAt;

    // 사업체 정보
    private String businessRegistNum;
    private String businessName;
    private String businessAddress;
    private String businessPost;

}
