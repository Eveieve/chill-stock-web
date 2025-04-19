package com.chilluminati.chillstock.nonuser.exception;

/**
 * 회원가입 과정에서 특정 에러코드를 담는 사용자 정의 예외 클래스
 */
public class SignUpException extends RuntimeException {
    private final SignUpErrorCode errorCode;

    public SignUpException(SignUpErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public SignUpErrorCode getErrorCode() {
        return errorCode;
    }
}
