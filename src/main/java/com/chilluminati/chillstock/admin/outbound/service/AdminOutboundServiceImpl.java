package com.chilluminati.chillstock.admin.outbound.service;

import com.chilluminati.chillstock.admin.outbound.common.RejectCode;
import com.chilluminati.chillstock.admin.outbound.dto.AdminOutboundDTO;
import com.chilluminati.chillstock.admin.outbound.repository.AdminOutboundRepository;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.chilluminati.chillstock.admin.outbound.common.RejectCode.*;

@Service
@RequiredArgsConstructor
public class AdminOutboundServiceImpl implements AdminOutboundService {
    private final AdminOutboundRepository adminOutboundRepository;

    @Override
    public List<AdminOutboundDTO> readAllOutboundRequest(Integer page, Integer limit) {
        Integer offset = (page - 1) * limit;
        return adminOutboundRepository.readAllOutboundRequest(offset, limit).stream().map(vo -> {
            return AdminOutboundDTO.builder()
                    .outbound_id(vo.getOutbound_id())
                    .outbound_date(vo.getOutbound_date())
                    .outbound_request_date(vo.getOutbound_request_date())
                    .outbound_status(vo.getOutbound_status())
                    .outbound_amount(vo.getOutbound_amount())
                    .product_name(vo.getProduct_name())
                    .reject_reason_message(vo.getReject_reason_message())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<AdminOutboundDTO> readOutboundRequestByStatus(String status, Integer page, Integer limit) {
        Integer offset = (page - 1) * limit;
        return adminOutboundRepository.readOutboundRequestByStatus(status, offset, limit).stream().map(vo -> {
            return AdminOutboundDTO.builder()
                    .outbound_id(vo.getOutbound_id())
                    .outbound_date(vo.getOutbound_date())
                    .outbound_request_date(vo.getOutbound_request_date())
                    .outbound_status(vo.getOutbound_status())
                    .outbound_amount(vo.getOutbound_amount())
                    .product_name(vo.getProduct_name())
                    .reject_reason_message(vo.getReject_reason_message())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Integer count(String status) {
        return adminOutboundRepository.count(status);
    }

    @Override
    public void updateOutboundStatus(String newStatus, Integer outboundId, String rejectReason) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();
        Integer adminId = adminOutboundRepository.getAdminId(userId);

        if (newStatus.equals("승인")) {
            adminOutboundRepository.updateOutboundStatus(newStatus, adminId, outboundId, rejectReason);
            List<Map<String, Integer>> stockAndOutboundAmount = adminOutboundRepository.getStockAndOutboundAmount(outboundId);
            Integer revenueAmount = stockAndOutboundAmount.get(0).get("stock_amount");
            Integer outboundAmount = stockAndOutboundAmount.get(0).get("outbound_amount");
            if (revenueAmount < outboundAmount) {
                throw new RuntimeException("출고수량이 재고수량보다 더 많습니다.");
            } else {
                adminOutboundRepository.updateStock(outboundId);
                if ((revenueAmount - outboundAmount) == 0) {
                    adminOutboundRepository.deleteZeroStock();
                }
            }
        } else if (newStatus.equals("반려")) {
            if (rejectReason.equals(RJ201.getCode())) {
                rejectReason = RJ201.getMsg();
            } else if (rejectReason.equals(RJ202.getCode())) {
                rejectReason = RJ202.getMsg();
            } else if (rejectReason.equals(RJ203.getCode())) {
                rejectReason = RJ203.getMsg();
            }
            adminOutboundRepository.updateOutboundStatus(newStatus, adminId, outboundId, rejectReason);
        }
    }
}
