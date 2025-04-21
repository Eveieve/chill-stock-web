package com.chilluminati.chillstock.member.outbound.controller;

import com.chilluminati.chillstock.member.outbound.service.MemberOutboundService;
import com.chilluminati.chillstock.member.outbound.vo.MemberOutboundVO;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberOutboundController {
    private final MemberOutboundService memberOutboundService;

    @GetMapping("/member/outbound-request")
    public String outboundRequest(@RequestParam(required = false) String productName, @RequestParam Integer productId, @RequestParam Integer outboundAmount, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        memberOutboundService.createOutboundRequest(productId, outboundAmount); // 삭제
        memberOutboundService.readAllMemberStock(userId, productName);
        return "member/outbound-request";
    }

    @PostMapping("/member/outbound-request")
    public String createOutboundRequest(@RequestParam Integer productId, @RequestParam Integer outboundAmount) {
        memberOutboundService.createOutboundRequest(productId, outboundAmount);
        return "redirect:/member/outbound-request";
    }
}
