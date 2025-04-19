package com.chilluminati.chillstock.nonuser.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/nonuser/login")
    public String loginForm() {
        return "nonuser/login";
    }

    @GetMapping("/nonuser/access-denied")
    public String loginError() {
        return "nonuser/access-denied"; // 로그인 실패시 보여줄 페이지 (선택사항)
    }


}