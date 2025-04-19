package com.chilluminati.chillstock.nonuser.testinfo;


import com.chilluminati.chillstock.security.EmailUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestInfo {
    @GetMapping("/nonuser/info")
    public String testInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();

        String email = userDetails.getUsername(); // 로그인 이메일
        Integer userId = userDetails.getUserId(); // DB PK
        String role = userDetails.getUserType(); // 권한

        model.addAttribute("email", email);
        model.addAttribute("userid", userId);
        model.addAttribute("role", role);
        return "nonuser/info";
    }
}
