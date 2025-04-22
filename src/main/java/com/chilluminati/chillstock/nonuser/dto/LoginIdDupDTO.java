package com.chilluminati.chillstock.nonuser.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 중복 아이디를 검사할때 받는 입력값
 */
@Data
@Builder
public class LoginIdDupDTO {
    @NotBlank(message = "{loginId.required}")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$", message = "{loginId.format}")
    private String userLoginId;
}
