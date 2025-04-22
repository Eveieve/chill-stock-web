package com.chilluminati.chillstock.admin.user.vo;

import lombok.Data;

@Data
public class UserBizVO {
    // 회원 정보
    private Integer userId;
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
