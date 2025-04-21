package com.chilluminati.chillstock.member.mypage.controller;

import com.chilluminati.chillstock.member.mypage.service.MemberMypageService;
import com.chilluminati.chillstock.security.EmailUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberMypageController {

    private final MemberMypageService memberMypageService;

    public MemberMypageController(MemberMypageService memberMypageService) {
        this.memberMypageService = memberMypageService;
    }

    @PostMapping("/member/delete")
    public String deleteAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        memberMypageService.deleteMyAccount(userId);

        return "redirect:/logout"; // 탈퇴 후 로그아웃 또는 홈 이동
    }
}
