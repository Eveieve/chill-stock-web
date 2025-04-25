package com.chilluminati.chillstock.member.iohistory.controller;

import com.chilluminati.chillstock.member.iohistory.dto.MemberIOHistoryInboundDTO;
import com.chilluminati.chillstock.member.iohistory.dto.MemberIOHistoryOutboundDTO;
import com.chilluminati.chillstock.member.iohistory.service.MemberIOHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberIOHistoryController {

    private final MemberIOHistoryService memberIOHistoryService;

    @GetMapping("/member/history")
    public String memberIOHistory(Model model) {
        List<MemberIOHistoryInboundDTO> inboundList = memberIOHistoryService.readInboundRequests();
        List<MemberIOHistoryOutboundDTO> outboundList = memberIOHistoryService.readOutboundRequests();

        model.addAttribute("inboundList", inboundList);
        model.addAttribute("outboundList", outboundList);
        return "/member/history";
    }
}