package com.chilluminati.chillstock.webclient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeoPoint {
    private String latitude; //위도 Y축
    private String longitude; //경고 X축
}