package com.chilluminati.chillstock.member.mypage.controller;

import com.chilluminati.chillstock.admin.user.exception.AdminUserException;
import com.chilluminati.chillstock.member.mypage.dto.MypageUpdateDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserBizDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserPasswordDTO;
import com.chilluminati.chillstock.member.mypage.service.MemberMypageService;
import com.chilluminati.chillstock.nonuser.service.NonUserService;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberMypageController {

    private final MemberMypageService memberMypageService;
    private final NonUserService nonUserService;


    /**
     * 회원 정보 조회
     * @param model
     * @return
     */
    @GetMapping("/mypage")
    public String showMyPage( Model model) {
        UserBizDTO user = memberMypageService.viewMyInfo();
        model.addAttribute("user", user);
        return "member/mypage";
    }


    /***
     * 회원 정보 수정
     * @param updateDTO
     * @return
     */
    @PostMapping("/mypage-edit")
    public String updateMyInfo(@RequestBody MypageUpdateDTO updateDTO) {


        // 내 정보 업데이트
        memberMypageService.updateMyInfo(updateDTO);

        return "redirect:/member/mypage";
    }

    /**
     * 현재 로그인한 유저 정보 가져오기
     * @return Map<String, Object>
     */
    @GetMapping("/getmyinfo")
    @ResponseBody
    public Map<String, Object> getMyInfo() {
        Map<String, Object> result = new HashMap<>();

//        EmailUserDetails emailUserDetails = getEmailUserDetails();
        UserBizDTO user = memberMypageService.viewMyInfo();
        log.info(user.toString());

        result.put("data", user);
        return result;
    }
    /***
     * 회원 탈퇴
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteAccount() {
        Map<String, Object> result = new HashMap<>();

        try {
            memberMypageService.deleteMyAccount();
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
        }

        return result;
    }

    /***
     * 비밀번호 변경
     */
    @PostMapping("/update-password")
    @ResponseBody
    public Map<String, Object> updatePassword(@RequestBody UserPasswordDTO userPasswordDTO) {
        Map<String, Object> result = new HashMap<>();

        result = memberMypageService.updateMemberPassword(userPasswordDTO);

        return result;
    }



    /***
     * get UserVO
     * @return
     */
    private EmailUserDetails getEmailUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Object> principal = Optional.ofNullable(authentication.getPrincipal());
        if(principal.orElse(null) instanceof EmailUserDetails) {
            System.out.println("성공");

            return (EmailUserDetails) authentication.getPrincipal();

        }
        else System.out.println("asdasdasdasdasdasdasdasdasdasd");
        return null;
    }
}
