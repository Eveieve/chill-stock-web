package com.chilluminati.chillstock.admin.user.exception;

public enum AdminUserErrorCode {
    USER_NOT_FOUND("해당 회원을 찾을 수 없습니다."),
    APPROVAL_FAILED("회원 승인에 실패했습니다."),
    DELETE_FAILED("회원 삭제에 실패했습니다.");

    private final String message;

    AdminUserErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
