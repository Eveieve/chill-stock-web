package com.chilluminati.chillstock;


import com.chilluminati.chillstock.admin.dashboard.DashBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final DashBoardRepository dashBoardRepository;

    @GetMapping("/")
    public String helloMain() {
        return "index";
    }

    @GetMapping("/admin/home")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, ChillStock!");
        Integer inboundCount = dashBoardRepository.countInbounds();
        model.addAttribute("inboundCount", inboundCount);
        Integer outboundCount = dashBoardRepository.countOutbounds();
        model.addAttribute("outboundCount", outboundCount);
        Integer inboundTodayCount = dashBoardRepository.countTodayInboundRequests();
        model.addAttribute("inboundTodayCount", inboundTodayCount);
        Integer outboundTodayCount = dashBoardRepository.countTodayOutboundRequests();
        model.addAttribute("outboundTodayCount", outboundTodayCount);
        return "admin/dashboard"; // → /WEB-INF/views/hello.html 로 매핑
    }

    @GetMapping("/member/home")
    public String helloUser(Model model) {
        model.addAttribute("message", "Hello, ChillStock!");
        return "member/dashboard"; // → /WEB-INF/views/hello.html 로 매핑
    }
}
