package com.chilluminati.chillstock.member.iohistory.service;

import com.chilluminati.chillstock.member.iohistory.dto.MemberIOHistoryInboundDTO;
import com.chilluminati.chillstock.member.iohistory.dto.MemberIOHistoryOutboundDTO;

import java.util.List;

public interface MemberIOHistoryService {
    List<MemberIOHistoryInboundDTO> readInboundRequests();
    List<MemberIOHistoryOutboundDTO> readOutboundRequests();
}
