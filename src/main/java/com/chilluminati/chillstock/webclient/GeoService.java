package com.chilluminati.chillstock.webclient;

import java.util.Optional;

public interface GeoService {
    Optional<GeoPoint> getGeoByAddress(String address);
}
