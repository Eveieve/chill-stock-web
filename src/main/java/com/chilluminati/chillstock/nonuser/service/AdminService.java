package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.nonuser.dto.UserDTO;

import java.util.List;

public interface AdminService {
    /**
     * 단일 회원을 삭제 처리
     * @param userId 삭제할 회원의 고유 ID
     */
    void deleteUser(Long userId);

    /**
     * 여러 명의 회원을 일괄 삭제 처리
     * @param userIds 삭제할 회원 ID 목록
     */
    void deleteUsers(List<Long> userIds);

    /**
     * 대기 상태의 단일 회원을 승인 처리합니다.
     * @param userId 승인할 회원의 고유 ID
     */
    void approveUser(Long userId);

    /**
     * 대기 상태의 여러 회원을 일괄 승인 처리
     * @param userIds 승인할 회원 ID 목록
     */
    void approveUsers(List<Long> userIds);


    /**
     * 삭제되었거나 탈퇴한 회원 목록을 조회
     * @return 탈퇴 회원의 DTO 리스트
     */
    List<UserDTO> getDeletedUsers();
}
