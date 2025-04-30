package com.chilluminati.chillstock.admin.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeletedUserDTO {
    // 기본 식별 정보 (Basic Identification)
    private String userLoginId;
    private String userName;
    private LocalDateTime userApprovedAt;

    // 사업체 이름은 운영상 필요할 수 있으므로 포함 (Business Name)
    private String businessName;

    // 민감 정보 - 뷰에서 마스킹하거나 최고 관리자만 접근 (Sensitive Info)
    private String userEmail;
    private String userPhone;
    private String businessRegistNum;
    private String businessAddress;
    private String businessPost;

}
