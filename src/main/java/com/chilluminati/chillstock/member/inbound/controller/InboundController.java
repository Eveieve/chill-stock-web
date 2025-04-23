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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class InboundController {

    private final InboundService inboundService;
    private final ProductService productService;


    @GetMapping("inbound-request")
    public String showInboundRequestForm(Model model,
                                         @RequestParam(defaultValue = "1") int page) {
        int pageSize = 10;

        // 전체 제품 수 → 총 페이지 수 계산
        int total = productService.getMyProductCount();
        int totalPages = (int) Math.ceil((double) total / pageSize);

        // 잘못된 페이지 요청 방어  공통부분으로 메서드 (람다
        if (page > totalPages) page = totalPages;
        if (page < 1) page = 1;

//        somethingdo(page )
//        somthiing();
//
//        //
//        private somethingdo(int page, int minus, Runnable runnable){
//            if(Math.(page minus)) > 0{runnable}
//            else(page - minus) {runnnable2}
//        }

        // 현재 페이지에 해당하는 제품만 가져옴
        List<ProductDTO> myProducts = productService.getMyPagedProducts(page, pageSize);

        // 모델에 데이터 전달
        model.addAttribute("myProducts", myProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageName", "입고 요청");

        return "member/inbound-request";
    }

    @PostMapping("inbound-request")
    public String requestInbound(InboundRequestDTO dto, RedirectAttributes redirectAttributes) {
        inboundService.registerInboundRequest(dto);

        // 리다이렉트 시 메시지 전달
        redirectAttributes.addFlashAttribute("successMessage", "입고 요청이 완료되었습니다.");

        return "redirect:/member/inbound-request";
    }
}
