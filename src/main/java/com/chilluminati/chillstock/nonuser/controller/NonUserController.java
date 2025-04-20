package com.chilluminati.chillstock.nonuser.controller;

import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.exception.SignUpException;
import com.chilluminati.chillstock.nonuser.service.UserService;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class NonUserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "nonuser/signupForm";
    }

    @PostMapping("/signup")
    public String signUp(SignUpDTO signUpDto, Model model) {
        try {
            userService.signUp(signUpDto);
            return "home/login";
        } catch (SignUpException e) {
            model.addAttribute("errorMessage", e.getErrorCode().getMessage());
            return "home/signup-form";
        }
    }

    /**
     * 비밀번호 찾기 클릭할 시, 띄워지는 로그인아이디 입력 모달
     * @return
     */
    @GetMapping("login/find-password")
    public String findByLoginIdForm() {
        return "home/findByLoginIdForm";
    }

    @GetMapping("login/reset-password")
    public String resetPasswordForm() {
        return "home/resetPasswordForm";
    }

    @PostMapping("/reset-password")
    public String resetPassword(PasswordResetDTO passwordResetDTO, Model model) {
        try {
            userService.resetPassword(passwordResetDTO);
            return "home/reset-password-success";
        } catch (SignUpException e) {
            model.addAttribute("errorMessage", e.getErrorCode().getMessage());
            return "home/reset-password-form";
        }
    }
}
