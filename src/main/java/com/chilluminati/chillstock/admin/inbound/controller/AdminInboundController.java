package com.chilluminati.chillstock.admin.inbound.controller;

import com.chilluminati.chillstock.admin.inbound.dto.AdminInboundRequestDTO;
import com.chilluminati.chillstock.admin.inbound.dto.InboundRejectCode;
import com.chilluminati.chillstock.admin.inbound.service.AdminInboundService;
import com.chilluminati.chillstock.admin.outbound.common.RejectCode;
import com.chilluminati.chillstock.member.inbound.service.InboundService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminInboundController {

    private final AdminInboundService adminInboundService;
    /**
     * 입고 요청 목록 페이지
     * @param status 상태 필터 (ex: "대기", "승인", "반려")
     * @param page 현재 페이지 (기본값: 1)
     * @param model 타임리프에 전달할 데이터
     * @return 목록 페이지 템플릿
     */
    @GetMapping("inbound")
    public String getInboundList(@RequestParam(required = false) String status,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 Model model) {
        int pageSize = 10;


        int offset = (page - 1) * pageSize;


        int totalCount = adminInboundService.countInbound(status);
        int totalPages = (int) Math.ceil((double) totalCount/ pageSize);

        // 현재 페이지 보정 (1이상 ,최대 페이지 이하)
        if (page < 1) page = 1;
        if (page > totalPages) page = totalPages;

        // 목록 조회
        List<AdminInboundRequestDTO> inboundList = adminInboundService.getInboundList(status, page, pageSize);
        // 🔥 디버깅용 로그 찍기
        System.out.println("조회된 입고요청 리스트 = " + inboundList);
        // 타임리프로 전달
        model.addAttribute("inboundList", inboundList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("status", status);

        // 여기 추가
        //
        model.addAttribute("rejectCodes", getInboundRejectCodeList());
        return "admin/inbound";

    }

    @PostMapping("/inbound/approve")
    public String approveInbound(@RequestParam("inboundIds") List<Integer> inboundIds) {
        adminInboundService.approveInboundRequests(inboundIds);
        return "redirect:/admin/inbound"; // 승인 후 목록 페이지로 리다이렉트
    }

    @PostMapping("/inbound/reject")
    public String rejectInbound(@RequestParam("inboundIds") List<Integer> inboundIds,
                                @RequestParam("rejectCode") String rejectCode) {
        adminInboundService.rejectInboundRequests(inboundIds, rejectCode);
        return "redirect:/admin/inbound"; // 반려 후 목록 페이지로 리다이렉트
    }

    private List<InboundRejectCode> getInboundRejectCodeList() {
        return List.of(
                new InboundRejectCode("REJ01", "공간 부족"),
                new InboundRejectCode("REJ02", "보관 온도 불일치"),
                new InboundRejectCode("REJ03", "시스템 내부 오류"),
                new InboundRejectCode("REJ04", "제품 정보 누락"),
                new InboundRejectCode("REJ05", "요청 데이터 이상")
        );
    }



}
