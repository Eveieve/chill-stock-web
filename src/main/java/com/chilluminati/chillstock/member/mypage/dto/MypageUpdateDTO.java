package com.chilluminati.chillstock.member.mypage.dto;

import lombok.Data;

@Data
public class MypageUpdateDTO {
    private String userName;
    private String userPhone;
    private String userEmail;

    private String businessName;
    private String businessRegistNum;
    private String businessAddress;
    private String businessPost;
}
