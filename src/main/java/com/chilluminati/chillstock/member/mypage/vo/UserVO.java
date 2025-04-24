package com.chilluminati.chillstock.member.mypage.vo;


import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.common.UserType;
import com.chilluminati.chillstock.nonuser.vo.BizVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 내정보 조회 vo
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserVO {
    private Integer userId;
    private String userLoginId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private UserType userType;
//    private String userPassword;
//    private LocalDate userApprovedAt;
//    private UserStatus userStatus;
//    private LocalDate userRequestedAt;
}