package com.chilluminati.chillstock.nonuser.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 회원가입 컨트롤러 또는 서비스에서 발생한 예외를 전역으로 받아 처리
 */
@ControllerAdvice(basePackages = "com.chilluminati.chillstock.nonuser")
public class SignUpExceptionHandler {

    /**
     * 스프링의 글로벌 예외 처리 메서드
     * 예외가 발생했을 때 컨트롤러에서 따로 try-catch 하지 않아도 자동으로 이 메서드가 호출됨
     * @param e
     * @param model
     * @return
     */
    @ExceptionHandler(SignUpException.class)
    public String handleSignUpException(SignUpException e, Model model) {
        model.addAttribute("errorMessage", e.getErrorCode().getMessage());
        return "/nonuser/signupForm"; // 실패 시 다시 회원가입 폼으로
    }

    @ExceptionHandler(Exception.class)
    public String handleUnexpectedException(Exception e, Model model) {
        model.addAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
        return "/nonuser/signupForm";
    }
}
