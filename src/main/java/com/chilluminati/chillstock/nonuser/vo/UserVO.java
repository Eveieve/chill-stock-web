package com.chilluminati.chillstock.nonuser.vo;

import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.common.UserType;
import lombok.*;

import java.time.LocalDate;

/**
 * 유저 테이블과 연결되는 VO
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserVO {
    private Integer userId;
    private String userLoginId;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userPhone;
    private LocalDate userApprovedAt;
    private UserType userType;
    private UserStatus userStatus;
    private LocalDate userRequestedAt;

    private BizVO bizVO;
}

