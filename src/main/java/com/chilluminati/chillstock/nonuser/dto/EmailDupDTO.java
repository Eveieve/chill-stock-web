package com.chilluminati.chillstock.nonuser.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 중복 이메일인지 검사할때 받는 입력값
 */
@Data
@Builder
public class EmailDupDTO {
    @NotBlank(message = "{email.required}")
    private String userEmail;
}
