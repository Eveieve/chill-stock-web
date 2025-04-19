package com.chilluminati.chillstock.nonuser.repository;

import com.chilluminati.chillstock.nonuser.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepo {
   void insertUser(UserVO user);  // 회원 가입
}
