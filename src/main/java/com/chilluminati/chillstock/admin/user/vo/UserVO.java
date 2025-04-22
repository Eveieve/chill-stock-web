package com.chilluminati.chillstock.admin.user.vo;

import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.common.UserType;
import com.chilluminati.chillstock.nonuser.vo.BizVO;
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

}
