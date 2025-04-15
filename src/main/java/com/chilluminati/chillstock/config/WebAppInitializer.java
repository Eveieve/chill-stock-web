package com.chilluminati.chillstock.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null; // DB 설정이 따로 있다면 여기에 추가
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { AppConfig.class }; // 위 AppConfig 등록
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // DispatcherServlet 매핑
    }
}