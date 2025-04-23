package com.chilluminati.chillstock.member.stock.controller;

import com.chilluminati.chillstock.member.stock.dto.MemberStockDTO;
import com.chilluminati.chillstock.member.stock.service.MemberStockService;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberStockController {

    private final MemberStockService memberStockService;

    @GetMapping("/member/stock")
    public String memberStock(@RequestParam(required = false) String productName, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "15") Integer limit, Model model) {
        List<MemberStockDTO> stockList = memberStockService.readAllMemberStockPaging(productName, page, limit);
        Integer totalCount = memberStockService.countMemberStock(productName); // 새로 추가할 메서드
        Integer totalPages = (int) Math.ceil((double) totalCount / limit);

        model.addAttribute("stockList", stockList);
        model.addAttribute("productName", productName);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "member/stock";
    }

    @GetMapping("/member/stock/search")
    public String memberStockSearch(@RequestParam(required = false) String productName, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "15") Integer limit, Model model) {
        List<MemberStockDTO> stockList = memberStockService.readAllMemberStockPaging(productName, page, limit);
        Integer totalCount = memberStockService.countMemberStock(productName); // 새로 추가할 메서드
        Integer totalPages = (int) Math.ceil((double) totalCount / limit);

        model.addAttribute("stockList", stockList);
        model.addAttribute("productName", productName);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "member/stock-search";
    }
}
