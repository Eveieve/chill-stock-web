package com.chilluminati.chillstock.admin.user.repository;


import com.chilluminati.chillstock.admin.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserRepository {
    Optional<UserVO> findByEmail(@Param("email") String email);
}
