package com.chilluminati.chillstock.nonuser.repository;

import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;

import com.chilluminati.chillstock.nonuser.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserRepo {
   /**
    * 회원계정 정보 넣기
    * @param user
    */
   void insertUser(UserVO user);  // 회원 가입

   /**
    * 기본키로 회원 정보만 조회하기
    * @param userId
    * @return
    */
   UserVO findByUserId(Integer userId);

   /**
    * 로그인 아이디로 회원정보만 조회하기
    * @param loginId
    * @return
    */
   UserVO findByLoginId(String loginId);

   /**
    *  이메일이 디비에 존재하는지 확인
    * @param email
    * @return 이미 존재하는 이메일이면 true 반환
    */
   boolean existsByEmail(String email);

   /**
    * 로그인아이디가 디비에 존재하는지 확인
    * @param loginId
    * @return
    */
   boolean existsByLoginId(String loginId);

   /**
    * email로 회원의 모든 정보 (비밀번호 포함) 불러오기
    * @param email
    * @return
    */
   UserVO findByEmail(String email);

   /**
    * 새로운 비밀번호를 저장하기
    * @param newPassword
    * @param userLoginId
    */
   void updatePassword(@Param("newPassword") String newPassword, @Param(("userLoginId")) String userLoginId);

   /**
    * 로그인 아이디 찾기
    * @param email
    * @return
    */
   String findLoginId(@Param("email") String email);

}
