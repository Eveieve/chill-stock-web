package com.chilluminati.chillstock;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hellotymeleaf")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, ChillStock!");
        return "hello"; // → /WEB-INF/views/hello.html 로 매핑
    }
}
