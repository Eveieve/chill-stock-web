package com.chilluminati.chillstock.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                ThymeleafConfig.class,
                HikariCPConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebMvcConfig.class }; // 위 AppConfig 등록
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // DispatcherServlet 매핑
    }
}