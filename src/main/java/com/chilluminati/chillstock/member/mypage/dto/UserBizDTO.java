package com.chilluminati.chillstock.member.mypage.dto;

import com.chilluminati.chillstock.admin.user.common.UserType;
import lombok.Data;

@Data
public class UserBizDTO {
    /** 유저ID */
    private String userLoginId;
    /** 유저 이메일 */
    private String userEmail;
    /** 유저 성명 */
    private String userName;
    /** 유저 휴대폰번호 */
    private String userPhone;
    /** 유저타입 */
    private UserType userType;

    /** 사업자명 */
    private String businessName;
    /** 사업자등록번호 */
    private String businessRegistNum;
    /** 사업자주소 */
    private String businessAddress;
    /** 사업자우편번호 */
    private String businessPost;
}
