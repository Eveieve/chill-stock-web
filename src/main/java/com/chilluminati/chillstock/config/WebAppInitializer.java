package com.chilluminati.chillstock.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                ThymeleafConfig.class,
                HikariCPConfig.class,
                MybatisConfig.class,
                SecurityConfig.class,
                AppConfig.class};// DB 설정이 따로 있다면 여기에 추가 기타 컨픽도 추가
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                WebMvcConfig.class }; // 위 AppConfig 등록
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // DispatcherServlet 매핑
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // DispatcherServlet 먼저 등록
        super.onStartup(servletContext);

        // CharacterEncodingFilter 등록
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}