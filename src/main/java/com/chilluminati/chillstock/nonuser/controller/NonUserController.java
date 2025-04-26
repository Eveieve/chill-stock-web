package com.chilluminati.chillstock.nonuser.controller;

import com.chilluminati.chillstock.nonuser.dto.EmailDupDTO;
import com.chilluminati.chillstock.nonuser.dto.LoginIdDupDTO;
import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.exception.SignUpException;
import com.chilluminati.chillstock.nonuser.service.NonUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nonuser")
public class NonUserController {
    private final NonUserService nonUserService;

    /**
     * 로그인 아이디를 찾기위해 이메일을 입력받는다.
     * @param email
     * @return loginId
     */
    @PostMapping("login/find-login-id")
    private String findLoginId(@RequestParam("email") String email) {
        return nonUserService.findLoginIdByEmail(email); // 로그인 아이디만 문자열로 응답하기. 로그인 아이디는 모달로 알려줌.
    }


    /**
     * 비밀번호 찾기 클릭할 시 find-password.html 로 이동한다
     * @return
     */
    @GetMapping("/find-password")
    public String showFindPasswordPage() {
        return "nonuser/find-password";
    }


    /**
     * 비밀번호를 찾는다(재설정한다)
     * @param dto 비밀번호 재설정을 위해 비밀번호 입력값
     * @param model
     * @return
     */
    @PostMapping("/find-password")
    public String findPassword(PasswordResetDTO dto, Model model) {
        try {
            // 1단계: 비밀번호가 아직 입력되지 않은 경우
            if (!hasPasswordInput(dto)) {
                if (!nonUserService.existsByLoginId(dto.getUserLoginId())) {
                    model.addAttribute("modalStep", "loginId"); // 다시 1단계로
                    model.addAttribute("errorMessage", "존재하지 않는 아이디입니다.");
                    return "nonuser/find-password";
                }
                // 아이디는 존재 → 2단계 비밀번호 입력으로 이동
                model.addAttribute("modalStep", "password");
                model.addAttribute("passwordResetDTO", dto);
                return "nonuser/find-password";
            }

            // 2단계: 비밀번호 입력 완료 → 재설정 수행
            nonUserService.resetPassword(dto);
            model.addAttribute("modalStep", "success");
            model.addAttribute("loginId", dto.getUserLoginId());


            // 성공 시 모달에서 확인 버튼으로 이동하도록 유지
            model.addAttribute("modalStep", "success");
            model.addAttribute("loginId", dto.getUserLoginId());
            return "nonuser/find-password";

        } catch (SignUpException e) {
            model.addAttribute("modalStep", "password");
            model.addAttribute("errorMessage", e.getErrorCode().getMessage());
            model.addAttribute("passwordResetDTO", dto);
            return "nonuser/find-password";
        }
    }

    /**
     * 회원가입 페이지로 이동한다.
     * @return
     */
    @GetMapping("/signup")
    public String showSignUpForm() {
        return "nonuser/signup";
    }

    /**
     * 이메일 중복 여부를 확인한다
     * @param emailDupDto 사용자가 입력한 이메일 Dto
     * @return 중복이면 true, 중복이 아니면 false
     */
    @PostMapping("signup/check-email")
    public boolean checkEmailDuplicate(@RequestBody @Valid EmailDupDTO emailDupDto) {
        return nonUserService.checkEmailDuplicate(emailDupDto);
        // 앞으로 넘겨줄때 true 이면 중복 메시지 사용자에게 띄우고, false 이면 중복 아니라는 메시지를 보여준다
    }

    /**
     * 로그인 아이디 중복 여부를 확인한다
     * @param loginIdDupDto 사용자가 입력한 로그인 아이디 Dto
     * @return 중복이면 true, 중복이 아니면 false
     */
    @PostMapping("signup/check-login-id")
    public boolean checkLoginIdDuplicate(@RequestBody @Valid LoginIdDupDTO loginIdDupDto) {
        return nonUserService.checkLoginIdDuplicate(loginIdDupDto);
        // 앞으로 넘겨줄때 true 이면 중복 메시지 사용자에게 띄우고, false 이면 중복 아니라는 메시지를 보여준다
    }

    /**
     * 회원가입 한다
     * @param signUpDto
     * @param model
     * @return
     */
    @PostMapping("/signup-submit")
    public String signUp(SignUpDTO signUpDto, Model model) {
        try {
            nonUserService.signUp(signUpDto);
            return "nonuser/login"; // 성공할시 로그인 페이지로 이동
        } catch (SignUpException e) {
            model.addAttribute("errorMessage", e.getErrorCode().getMessage());
            return "nonuser/signup"; // 실패할 시 회원가입 페이지로 다시 감
        }
    }

    /**
     * 로그인 아이디 입력후, 비밀번호 재설정 단계로 넘어왔는지 확인한다
     * @param dto
     * @return 로그인 아이디 입력해 존재하는 사용자로 판단됐으면 true
     */
    private boolean hasPasswordInput(PasswordResetDTO dto) {
        return dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()
                && dto.getNewPasswordCheck() != null && !dto.getNewPasswordCheck().isEmpty();
    }

}
