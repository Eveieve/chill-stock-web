package com.chilluminati.chillstock.admin.warehouse.service.func;

import com.chilluminati.chillstock.security.EmailUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class getAuthUserIdDetails implements Supplier<Integer> {
    @Override
    public Integer get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .filter(EmailUserDetails.class::isInstance)
                .map(EmailUserDetails.class::cast)
                .map(EmailUserDetails::getUserId)
                .orElseThrow(() -> new IllegalStateException("인증된 사용자 정보 없음"));
    }
}
