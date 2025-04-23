package com.chilluminati.chillstock.admin.user.vo;

import com.chilluminati.chillstock.admin.user.common.UserType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserBizBackupVO {
    private Integer backupId;
    private String userLoginId;
    private String userEmail;
    private String userName;
    private String userPhone;
    private UserType userType;
    private LocalDateTime deletedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime requestedAt;
    private String businessRegistNum;
    private String businessName;
    private String businessAddress;
    private String businessPost;
}
