package com.chilluminati.chillstock.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

//
@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        log.info("로그인 성공, 권한 목록: {}", roles);

        if (roles.contains("ROLE_admin")) {
            response.sendRedirect("/admin/home");
        } else if (roles.contains("ROLE_member")) {
            response.sendRedirect("/member/home");
        } else {
            response.sendRedirect("/"); // 기본 fallback
        }
    }
}