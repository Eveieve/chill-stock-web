package com.chilluminati.chillstock.admin.outbound.common;

import lombok.Getter;

@Getter
public enum RejectCode {
    RJ201("유통기한 만료", "RJ201"),
    RJ202("출고 한도 초과", "RJ202"),
    RJ203("기타", "RJ203"),;

    private final String msg;
    private final String code;

    RejectCode(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
