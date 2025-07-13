package com.chilluminati.chillstock.member.mypage.repository;

import com.chilluminati.chillstock.member.mypage.dto.MypageUpdateDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserPasswordDTO;
import com.chilluminati.chillstock.member.mypage.vo.BizVO;
import com.chilluminati.chillstock.member.mypage.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMypageRepo {
    /** 회원 삭제 */
    void deleteByUserId(@Param("userId") Integer userId);

    /** 회원 정보 수정 */
    void updateUser(@Param("userId") Integer userId, @Param("dto") MypageUpdateDTO dto);
    /** 사업자 정보 수정 */
    void updateBiz(@Param("userId") Integer userId, @Param("dto") MypageUpdateDTO dto);

    /** 유저id로 User 찾기 */
    UserVO findUserById(@Param("userId") Integer userId);
    /** 유저id로 사업자 찾기 */
    List<BizVO> findBizByUserId(@Param("userId") Integer userId);

    /** 유저패스워드 변경 */
    void updateUserPassword(@Param("userId") Integer userId, @Param("dto") UserPasswordDTO userPasswordDTO);
}
