package com.chilluminati.chillstock.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoGeoService implements GeoService {

    private final WebClient kakaoWebClient; // 웹클라이언트 객체

    @Override
    public Optional<GeoPoint> getGeoByAddress(String address) {
        KakaoGeoResponse response = kakaoWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/local/search/address.json")
                        .queryParam("query", address)
                        .build()) //uri 요청
                .retrieve() //응답
                .bodyToMono(KakaoGeoResponse.class) //json 맵핑
                .block(); // ← 블로킹(webflux 일떈 넌블록, 비동기 방식 사용)

        return Optional.ofNullable(response)
                .map(KakaoGeoResponse::getDocuments)
                .filter(docs -> docs != null && !docs.isEmpty())
                .map(docs -> docs.get(0))
                .map(doc -> new GeoPoint(doc.getY(), doc.getX()));
    }
}
