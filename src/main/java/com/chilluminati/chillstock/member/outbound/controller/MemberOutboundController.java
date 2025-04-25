package com.chilluminati.chillstock.member.outbound.controller;

import com.chilluminati.chillstock.member.outbound.service.MemberOutboundService;
import com.chilluminati.chillstock.member.outbound.vo.MemberOutboundVO;
import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberOutboundController {
    private final MemberOutboundService memberOutboundService;

    @GetMapping("/member/outbound-request")
    public String outboundRequest(@RequestParam(required = false) String productName, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, Model model) {
        List<MemberStockDTO> stockList = memberOutboundService.readAllMemberStock(productName, page, limit);
        Integer totalCount = memberOutboundService.countMemberStock(productName);
        Integer totalPages = (int) Math.ceil((double) totalCount / limit);

        model.addAttribute("stockList", stockList);
        model.addAttribute("productName", productName);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "member/outbound-request";
    }

    @GetMapping("/member/outbound-request/search")
    public String outboundRequestSearch(@RequestParam(required = false) String productName, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, Model model) {
        List<MemberStockDTO> stockList = memberOutboundService.readAllMemberStockByProductName(productName, page, limit);
        Integer totalCount = memberOutboundService.countMemberStock(productName);
        Integer totalPages = (int) Math.ceil((double) totalCount / limit);

        model.addAttribute("stockList", stockList);
        model.addAttribute("productName", productName);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "member/outbound-request-search";
    }

    @PostMapping("/member/outbound-request")
    public String createOutboundRequest(@RequestParam Integer productId, @RequestParam Integer outboundAmount) {
        memberOutboundService.createOutboundRequest(productId, outboundAmount);
        return "redirect:/member/outbound-request";
    }

    @PostMapping("/member/outbound-request/search")
    public String createOutboundRequestSearch(@RequestParam Integer productId, @RequestParam Integer outboundAmount) {
        memberOutboundService.createOutboundRequest(productId, outboundAmount);
        return "redirect:/member/outbound-request";
    }
}
