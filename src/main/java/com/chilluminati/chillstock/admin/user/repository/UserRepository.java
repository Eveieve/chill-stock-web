package com.chilluminati.chillstock.admin.user.repository;

import com.chilluminati.chillstock.admin.user.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface UserRepository {
    Optional<UserVo> findByEmail(@Param("email") String email);
}
