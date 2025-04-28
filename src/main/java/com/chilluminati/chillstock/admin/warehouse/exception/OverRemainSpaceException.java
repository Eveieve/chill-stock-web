package com.chilluminati.chillstock.admin.warehouse.exception;


import lombok.Getter;

@Getter
public class OverRemainSpaceException extends RuntimeException {
    public OverRemainSpaceException(String message) {
        super(message);
    }
}