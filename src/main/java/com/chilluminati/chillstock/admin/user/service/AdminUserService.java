package com.chilluminati.chillstock.admin.user.service;

import com.chilluminati.chillstock.admin.user.vo.UserBizBackupVO;
import com.chilluminati.chillstock.admin.user.vo.UserVO;

import java.util.List;

public interface AdminUserService {

    UserVO findUserById(Integer userId);



    /**
     * 단일 회원을 삭제 처리
     * @param userId 삭제할 회원의 고유 ID
     */
    void deleteUserById(Integer userId);

    /**
     * 여러 명의 회원을 일괄 삭제 처리
     * @param userIds 삭제할 회원 ID 목록
     */
    void deleteUsersByIds(List<Integer> userIds);

    /**
     * 대기 상태의 단일 회원을 승인 처리
     * @param userId 승인할 회원의 고유 ID
     */
    void approveUserById(Integer userId);

    /**
     * 대기 상태의 여러 회원을 일괄 승인 처리
     * @param userIds 승인할 회원 ID 목록
     */
    void approveUsersByIds(List<Integer> userIds);


    /**
     * 삭제되었거나 탈퇴한 회원 목록을 조회
     * @return 탈퇴 회원의 DTO 리스트
     */
   // List<UserDTO> getDeletedUsers();

    List<UserBizBackupVO> getAllDeletedUsers();
}
