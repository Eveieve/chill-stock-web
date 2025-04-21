package com.chilluminati.chillstock.member.mypage.repository;

import org.apache.ibatis.annotations.Param;

public interface MemberMypageRepo {
    void deleteByUserId(@Param("userId") Integer userId);
}
