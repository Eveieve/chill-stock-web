package com.chilluminati.chillstock.common;

import java.util.Collections;
import java.util.List;

public class ResultList<T> {

    private final T data;
    private final String message;
    private final boolean success;

    public ResultList(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public static <T> ResultList<T> success(T data, String message) {
        return new ResultList<>(data, message, true);
    }

    public static <T> ResultList<List<T>> failure(String message) {
        return new ResultList<>(null, message, false);
    }

    public static <T> ResultList<List<T>> emptyList(String message) {
        return new ResultList<>(Collections.emptyList(), message, true);
    }
}
