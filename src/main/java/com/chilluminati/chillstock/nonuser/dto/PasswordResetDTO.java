package com.chilluminati.chillstock.nonuser.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class PasswordResetDTO {
    @NotBlank(message = "{password.required}")
    @Size(min = 8, max = 20, message = "{password.length}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*]+$", message = "{password.format}")
    private String newPassword;

    @NotBlank(message = "{passwordCheck.required}")
    private String newPasswordCheck;
    private String userLoginId;
    // getter/setter
}
