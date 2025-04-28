package com.chilluminati.chillstock.admin.inbound.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InboundRejectCode {
    private String code;
    private String msg;

}