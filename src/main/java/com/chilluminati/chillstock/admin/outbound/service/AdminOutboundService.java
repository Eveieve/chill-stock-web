package com.chilluminati.chillstock.admin.outbound.service;

import com.chilluminati.chillstock.admin.outbound.dto.AdminOutboundDTO;
import com.chilluminati.chillstock.admin.outbound.vo.AdminOutboundVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminOutboundService {
    List<AdminOutboundDTO> readAllOutboundRequest(Integer page, Integer limit);
    List<AdminOutboundDTO> readOutboundRequestByStatus(String status, Integer page, Integer limit);
    Integer count(String status);
    void updateOutboundStatus(String newStatus, Integer outboundId, String rejectReason);
}
