package com.chilluminati.chillstock.member.inbound.controller;

import com.chilluminati.chillstock.member.inbound.dto.InboundRequestDTO;
import com.chilluminati.chillstock.member.inbound.service.InboundService;
import com.chilluminati.chillstock.member.product.dto.ProductDTO;
import com.chilluminati.chillstock.member.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class InboundController {

    private final InboundService inboundService;
    private final ProductService productService;


    @GetMapping("inbound-request")
    public String showInboundRequestForm(Model model) {
        List<ProductDTO> myProducts = productService.getMyProducts();

        model.addAttribute("myProducts", myProducts); // View에서 사용
        model.addAttribute("pageName", "입고 요청");

        // 제품리스트들 뿌려줄 것
        return "member/inbound-request";
    }

    @PostMapping("inbound-request")
    public String requestInbound(InboundRequestDTO dto) {
        inboundService.registerInboundRequest(dto);
        return "redirect:/member/inbound-history";
    }
}
