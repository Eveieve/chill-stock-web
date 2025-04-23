package com.chilluminati.chillstock.admin.user.exception;

public class AdminUserException extends RuntimeException{
    private final AdminUserErrorCode errorCode;

    public AdminUserException(AdminUserErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public AdminUserErrorCode getErrorCode() {
        return errorCode;
    }
}
