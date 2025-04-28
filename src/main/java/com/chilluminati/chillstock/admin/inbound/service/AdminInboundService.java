package com.chilluminati.chillstock.admin.inbound.service;

import com.chilluminati.chillstock.admin.inbound.dto.AdminInboundRequestDTO;

import java.util.List;
import java.util.Map;

public interface AdminInboundService {

    List<AdminInboundRequestDTO> getInboundList(String status, int page, int size);

    int countInbound(String status);


    // 입고요청 승인 처리
    Map<String, Integer> approveInboundRequests(List<Integer> inboundIds);

    void rejectInboundRequests(List<Integer> inboundIds, String rejectCode);



}
