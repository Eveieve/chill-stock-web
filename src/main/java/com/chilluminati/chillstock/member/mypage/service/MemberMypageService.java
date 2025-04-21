package com.chilluminati.chillstock.member.mypage.service;

import org.apache.ibatis.annotations.Param;

public interface MemberMypageService {

    void deleteMyAccount(@Param("userId") Integer userId);
}
