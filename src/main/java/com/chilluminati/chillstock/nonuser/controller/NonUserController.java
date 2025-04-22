package com.chilluminati.chillstock.nonuser.controller;

import com.chilluminati.chillstock.nonuser.dto.LoginIdDupDTO;
import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.exception.SignUpException;
import com.chilluminati.chillstock.nonuser.exception.UserNotFoundException;
import com.chilluminati.chillstock.nonuser.service.UserService;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class NonUserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "nonuser/signupForm";
    }

    /**
     * 회원가입 한다
     * @param signUpDto
     * @param model
     * @return
     */
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
     * 로그인 아이디 중복 여부를 확인한다
     * @param loginIdDupDto 사용자가 입력한 로그인 아이디 Dto
     * @return 중복이면 true, 중복이 아니면 false
     */
    @PostMapping("/check-login-id")
    public boolean checkLoginIdDuplicate(@RequestBody @Valid LoginIdDupDTO loginIdDupDto) {
        return userService.checkLoginIdDuplicate(loginIdDupDto);
        // 앞으로 넘겨줄때 true 이면 중복 메시지 사용자에게 띄우고, false 이면 중복 아니라는 메시지를 보여준다
    }


    /**
     * 이메일을 이용해 로그인 아이디를 반환한다.
     * @param email
     * @return
     */
    @PostMapping("/find-login-id")
    public String findLoginId(@RequestParam("email") String email) {
        return userService.findLoginId(email); // 로그인 아이디만 문자열로 응답하기. 로그인 아이디는 모달로 알려줌.
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
