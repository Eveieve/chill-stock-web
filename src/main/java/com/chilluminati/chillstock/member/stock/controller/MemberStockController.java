package com.chilluminati.chillstock.member.stock.controller;

import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;
import com.chilluminati.chillstock.member.stock.service.MemberStockService;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberStockController {

    private final MemberStockService memberStockService;

    @GetMapping("/member/stock")
    public String memberStock(@RequestParam(required = false) String productName, Model model) { //@으로 user_id 파라미터
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        List<MemberStockDTO> stockList = memberStockService.readAllMemberStock(userId, productName);
        model.addAttribute("stockList", stockList);
        model.addAttribute("productName", productName);
        return "member/stock";
    }
}
