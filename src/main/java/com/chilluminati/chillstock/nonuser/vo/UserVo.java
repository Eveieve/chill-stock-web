package com.chilluminati.chillstock.nonuser.vo;

import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.common.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVo {
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
}

