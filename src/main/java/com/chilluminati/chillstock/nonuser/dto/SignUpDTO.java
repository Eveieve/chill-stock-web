package com.chilluminati.chillstock.nonuser.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class SignUpDTO {

    @NotBlank(message = "{loginId.required}")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$", message = "{loginId.format}")
    private String userLoginId;

    @NotBlank(message = "{name.required}")
    @Pattern(regexp = "^[가-힣a-zA-Z\\s]{2,30}$", message = "{name.format}")
    private String userName;

    @NotBlank(message = "{email.required}")
    @Email (message = "{email.invalid)")
    private String userEmail;

    @Pattern(regexp = "^(01[016789])-?\\d{3,4}-?\\d{4}$|^\\+1-\\d{3}-\\d{3}-\\d{4}$", message = "{phone.format}")
    private String userPhone;

    @NotBlank(message = "{password.required}")
    @Size(min = 8, max = 20, message = "{password.length}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*]+$", message = "{password.format}")
    private String userPassword;

    @NotBlank(message = "{passwordCheck.required}")
    private String userPasswordCheck;

    // 사업체 정보
    @Size(max = 50, message = "{bizName.size}")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s]+$", message = "{bizName.pattern}")
    private String businessName;

    @NotBlank(message = "{bizNumber.required}")
    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "{bizNumber.pattern}")
    private String businessRegistNum;

    @NotBlank(message = "{bizAddress.required}")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s,.-]{5,100}$", message = "{bizAddress.format}")
    private String businessAddress;

    @NotBlank(message = "{bizPost.required}")
    @Pattern(regexp = "^\\d{5}$|^[A-Z]\\d[A-Z] ?\\d[A-Z]\\d$", message = "{bizPost.format}")
    private String businessPost;

}
