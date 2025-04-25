package com.chilluminati.chillstock.admin.outbound.controller;

import com.chilluminati.chillstock.admin.outbound.common.RejectCode;
import com.chilluminati.chillstock.admin.outbound.dto.AdminOutboundDTO;
import com.chilluminati.chillstock.admin.outbound.service.AdminOutboundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminOutboundController {

    private final AdminOutboundService adminOutboundService;

    @GetMapping("/admin/outbound")
    public String adminOutbound(@RequestParam(required = false) String status, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, Model model) {
        List<AdminOutboundDTO> outboundList = adminOutboundService.readAllOutboundRequest(page, limit);
        Integer totalCount = adminOutboundService.count(status);
        Integer totalPages = (int) Math.ceil((double) totalCount / limit);

        model.addAttribute("outboundList", outboundList);
        model.addAttribute("status", status);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("rejectCodes", RejectCode.values());

        return "admin/outbound";
    }

    @GetMapping("admin/outbound/sort")
    public String adminOutboundSort(@RequestParam String status, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, Model model) {
        List<AdminOutboundDTO> outboundList = adminOutboundService.readOutboundRequestByStatus(status, page, limit);
        Integer totalCount = adminOutboundService.count(status);
        Integer totalPages = (int) Math.ceil((double) totalCount / limit);

        model.addAttribute("outboundList", outboundList);
        model.addAttribute("status", status);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("rejectCodes", RejectCode.values());
        return "admin/outbound-sort";
    }

    @PostMapping("/admin/outbound")
    public String adminOutboundPost(@RequestParam String newStatus, @RequestParam(required = false) String rejectCode, @RequestParam Integer outboundId) {
        adminOutboundService.updateOutboundStatus(newStatus, outboundId, rejectCode);
        return "redirect:/admin/outbound";
    }

    @PostMapping("/admin/outbound/sort")
    public String adminOutboundPostSort(@RequestParam String newStatus, @RequestParam String rejectCode, @RequestParam Integer outboundId) {
        adminOutboundService.updateOutboundStatus(newStatus, outboundId, rejectCode);
        return "redirect:/admin/outbound";
    }
}
