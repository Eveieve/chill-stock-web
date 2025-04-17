package com.chilluminati.chillstock.config;


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

}
