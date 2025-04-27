package com.chilluminati.chillstock.admin.inbound.service;

import com.chilluminati.chillstock.admin.inbound.dto.AdminInboundRequestDTO;

import java.util.List;

public interface AdminInboundService {

    List<AdminInboundRequestDTO> getInboundList(String status, int page, int size);

    int countInbound(String status);


    // 입고요청 승인 처리
    void approveInboundRequests(List<Integer> inboundIds);

    void rejectInboundRequests(List<Integer> inboundIds, String rejectCode);



}
