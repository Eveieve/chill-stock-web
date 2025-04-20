package com.chilluminati.chillstock.nonuser.repository;

import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepo {
   void insertUser(UserVO user);  // 회원 가입

   // 테이블 기본키로 회원 찾기
   UserVO findByUserId(Integer userId);

   // 로그인 아이디 로 회원의 모든 정보  불러오기
   UserVO findByLoginId(String loginId);

   /**
    * email로 회원의 모든 정보 (비밀번호 포함) 불러오기
    * @param email
    * @return
    */
   UserVO findByEmail(String email);

   /**
    * 사용자로부터 새 비밀번호를 받아 비밀번호를 재설정하기
    * @param newPassword
    */
   void updatePassword(@Param("newPassword") String newPassword, @Param(("userLoginId")) String userLoginId);

   /**
    * 이메일로 아이디 찾기
    * @param email
    */
   String findLoginId(@Param("email") String email);
}
