package com.chilluminati.chillstock.admin.user.service;

import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.dto.DeletedUserDTO;
import com.chilluminati.chillstock.admin.user.dto.UserBizDTO;
import com.chilluminati.chillstock.admin.user.vo.UserBizBackupVO;
import com.chilluminati.chillstock.admin.user.vo.UserBizVO;
import com.chilluminati.chillstock.admin.user.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserService {

    /**
     * 전체 회원 수 세기
     * @return
     */
    int countAllUsers();

    /**
     * 삭제된 전체 회원 수 세기
     */
    int countAllDeleted();

    /**
     * 전체 회원 조회하기 (페이징)
     * @param limit
     * @return
     */
    List<UserBizDTO> getAllUsersByPage(@Param("limit") int limit);

    /**
     * 대기중 회원 모두 조회하기
     * @param
     * @return
     */
    List<UserBizDTO> getPendingUsersByPage(int page);

    /**
     * 이름으로 회원계정을 검색
     * @param userName
     * @return
     */
    List<UserBizDTO> searchUserByName(String userName);

    /**
     * 회원 계정 정보 상세 조회하기
     * @param
     * @return
     */
    UserBizDTO viewUserDetail(Integer userId);

    /**
     * 여러 명의 회원을 일괄 삭제 처리
     * @param userIds 삭제할 회원 ID 목록
     */
    void deleteUsersByIds(List<Integer> userIds);


    /**
     * 대기 상태의 여러 회원을 일괄 승인 처리
     * @param userIds 승인할 회원 ID 목록
     */
    void approveUsersByIds(List<Integer> userIds);


    /**
     * 삭제되었거나 탈퇴한 회원 목록을 조회
     * @return 탈퇴 회원의 DTO 리스트
     */
    List<DeletedUserDTO> getAllDeletedByPage(@Param("limit") int limit);


}
