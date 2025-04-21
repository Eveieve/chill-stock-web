package com.chilluminati.chillstock.member.mypage.service;

import com.chilluminati.chillstock.member.mypage.dto.MypageUpdateDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserBizDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserPasswordDTO;
import com.chilluminati.chillstock.member.mypage.repository.MemberMypageRepo;
import com.chilluminati.chillstock.member.mypage.vo.BizVO;
import com.chilluminati.chillstock.member.mypage.vo.UserVO;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberMypageServiceImpl implements MemberMypageService {

    private final MemberMypageRepo memberMypageRepo;

    /***
     * 회원 정보 조회
     * @return
     */
    @Override
    public UserBizDTO viewMyInfo() {
        EmailUserDetails userDetails = getEmailUserDetails();
        Integer userId = userDetails.getUserId(); // extract userId

        UserVO user = memberMypageRepo.findUserById(userId);
        BizVO biz = memberMypageRepo.findBizByUserId(userId);

        UserBizDTO dto = new UserBizDTO();

        //1. 유저 정보 세팅
        dto.setUserLoginId(user.getUserLoginId());
        dto.setUserEmail(user.getUserEmail());
        dto.setUserName(user.getUserName());
        dto.setUserPhone(user.getUserPhone());
        dto.setUserType(user.getUserType());

        //2. 사업자 정보 세팅
        dto.setBusinessName(biz.getBusinessName());
        dto.setBusinessRegistNum(biz.getBusinessRegistNum());
        dto.setBusinessAddress(biz.getBusinessAddress());
        dto.setBusinessPost(biz.getBusinessPost());

        return dto;
    }

    /**
     * 회원 비번 변경
     */
    @Override
    public void updateMemberPassword(UserPasswordDTO userPasswordDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptPassword = encoder.encode(userPasswordDTO.getUserPassword());

        userPasswordDTO.setUserPassword(encryptPassword);

        log.debug("#########################");
        log.debug("userPassword: {}", userPasswordDTO.getUserPassword());

        EmailUserDetails userDetails = getEmailUserDetails();
        Integer userId = userDetails.getUserId();
        // 1. userId를 기준으로 회원 비밀번호 수정
        memberMypageRepo.updateUserPassword(userId, userPasswordDTO);
    }

    /***
     * 회원 탈퇴
     */
    @Override
    public void deleteMyAccount() {
        EmailUserDetails userDetails = getEmailUserDetails(); // get UserVO
        Integer userId = userDetails.getUserId(); // extract userId

        //1. 회원 pk 기준으로 db에서 삭제
        memberMypageRepo.deleteByUserId(userId);
    }

    /***
     * 회원 정보 수정
     * @param dto
     */
    @Override
    public void updateMyInfo(MypageUpdateDTO dto) {
        EmailUserDetails userDetails = getEmailUserDetails(); // get UserVO
        Integer userId = userDetails.getUserId(); // extract userid

        // 1. userId를 기준으로 회원 정보 수정
        memberMypageRepo.updateUser(userId, dto);
        // 2. userId 기준으로 사업자 정보 수정
        memberMypageRepo.updateBiz(userId, dto);
    }

    /***
     * get UserVO
     * @return
     */
    private EmailUserDetails getEmailUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (EmailUserDetails) authentication.getPrincipal(); // extract userVO
    }
}
