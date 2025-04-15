package com.chilluminati.chillstock.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ThymeleafConfig.class)
class WebMvcConfigTest {
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Test
    public void thymeleafEngineIsNotNull() {
        assertNotNull(templateEngine);
    }

}