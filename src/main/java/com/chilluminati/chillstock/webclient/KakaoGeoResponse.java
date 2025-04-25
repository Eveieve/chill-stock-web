package com.chilluminati.chillstock.webclient;

import lombok.Data;

import java.util.List;

@Data
public class KakaoGeoResponse {
    private List<Document> documents;

    @Data
    public static class Document {
        private String address_name;
        private String x; // 경도
        private String y; // 위도
    }
}