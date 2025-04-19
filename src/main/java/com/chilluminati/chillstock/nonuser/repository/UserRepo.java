package com.chilluminati.chillstock.nonuser.repository;

import com.chilluminati.chillstock.nonuser.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepo {
   void insertUser(UserVO user);  // 회원 가입

   // loginId로 유저테이블의 UserVO 불러오기
   UserVO findByLoginId(String loginId);
}
