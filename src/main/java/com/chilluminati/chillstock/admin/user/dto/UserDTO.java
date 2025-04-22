package com.chilluminati.chillstock.admin.user.dto;

import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.common.UserType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer userId;
    private String userLoginId;
    private String userEmail;
    private String userName;

    private String userPhone;
    private LocalDate userApprovedAt;
    private UserType userType;
    private UserStatus userStatus;
    private LocalDate userRequestedAt;
}


