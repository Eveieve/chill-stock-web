package com.chilluminati.chillstock.member.mypage.service;

import com.chilluminati.chillstock.member.mypage.repository.MemberMypageRepo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMypageServiceImpl implements MemberMypageService {

    private final MemberMypageRepo memberMypageRepo;

    @Override
    public void deleteMyAccount(@Param("userId") Integer userId) {
        memberMypageRepo.deleteByUserId(userId);
    }
}
