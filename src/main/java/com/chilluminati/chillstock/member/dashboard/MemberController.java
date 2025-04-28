package com.chilluminati.chillstock.member.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pageName", "Dashboard");
        return "member/dashboard";
    }
}
