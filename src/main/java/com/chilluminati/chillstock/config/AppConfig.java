package com.chilluminati.chillstock.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(
        basePackages = "com.chilluminati.chillstock",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "com\\.chilluminati\\.chillstock\\.config\\..*"
        )
)
public class AppConfig {

        // modelMapper 사용하기 위한 설정
        @Bean
        public ModelMapper modelMapper() {
                return new ModelMapper();
        }
}
