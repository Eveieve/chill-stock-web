package com.chilluminati.chillstock.nonuser.exception;

/**
 * 회원가입 과정에서 발생할 수 있는 예외들을 enum으로 정의
 */
public enum
SignUpErrorCode {
    DUPLICATE_LOGIN_ID("이미 사용 중인 로그인 ID입니다."),
    DUPLICATE_EMAIL("이미 등록된 이메일입니다."),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다."),
    INVALID_INPUT("입력값이 누락되었거나 형식이 올바르지 않습니다."),
    DATABASE_ERROR("회원가입 중 오류가 발생했습니다.");

    private final String message;

    SignUpErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
