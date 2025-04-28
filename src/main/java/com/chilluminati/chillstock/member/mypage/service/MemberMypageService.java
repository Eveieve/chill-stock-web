package com.chilluminati.chillstock.member.mypage.service;

import com.chilluminati.chillstock.member.mypage.dto.MypageUpdateDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserBizDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserPasswordDTO;

import java.util.Map;

public interface MemberMypageService {
    /** 회원 탈퇴 */
    void deleteMyAccount();

    /** 회원 정보 수정 */
    void updateMyInfo(MypageUpdateDTO updateDTO);

    /** 회원 정보 조회 */
    UserBizDTO viewMyInfo();

    /**
     * 회원 비번 변경
     *
     * @return
     */
    Map<String, Object> updateMemberPassword(UserPasswordDTO userPasswordDTO);
}
