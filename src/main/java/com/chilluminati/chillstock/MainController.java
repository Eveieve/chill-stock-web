package com.chilluminati.chillstock;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String helloMain() {
        return "index";
    }

    @GetMapping("/admin/home")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, ChillStock!");
        return "admin/dashboard"; // → /WEB-INF/views/hello.html 로 매핑
    }

    @GetMapping("/member/home")
    public String helloUser(Model model) {
        model.addAttribute("message", "Hello, ChillStock!");
        return "member/dashboard"; // → /WEB-INF/views/hello.html 로 매핑
    }
}
