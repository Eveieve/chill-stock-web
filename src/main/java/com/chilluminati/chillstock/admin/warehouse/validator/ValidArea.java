package com.chilluminati.chillstock.admin.warehouse.validator;

import com.chilluminati.chillstock.admin.warehouse.repository.AdminAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ValidArea {
    /**
     * 검증후 리턴
     * @param a 입력값(리턴값)
     * @param b 비교값
     * @param comparator 비교
     * @param errorMessage 메시지
     * @return a
     * @param <T> a,b 타입
     */
    public <T> T validateAndReturn(T a, T b, Comparator<T> comparator, String errorMessage) {
        return Optional.ofNullable(a)
                .filter(value -> comparator.compare(value, b) <= 0)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
