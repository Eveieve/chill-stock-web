package com.chilluminati.chillstock.webclient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeoPoint {
    private String latitude; //위도 Y축
    private String longitude; //경고 X축

    /**
     * Haversine 알고리즘 위도경도
     * @param other 다른 GeoPoint
     * @return km
     */
    public int distanceTo(GeoPoint other) {
        final int EARTH_RADIUS_KM = 6371;

        double lat1 = Math.toRadians(Double.parseDouble(this.latitude));
        double lon1 = Math.toRadians(Double.parseDouble(this.longitude));
        double lat2 = Math.toRadians(Double.parseDouble(other.latitude));
        double lon2 = Math.toRadians(Double.parseDouble(other.longitude));

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS_KM * c;

        return (int) Math.round(distance); // 반올림 후 int로 변환
    }
}