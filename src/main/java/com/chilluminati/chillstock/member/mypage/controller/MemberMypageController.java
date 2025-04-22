package com.chilluminati.chillstock.member.mypage.controller;

import com.chilluminati.chillstock.member.mypage.dto.MypageUpdateDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserBizDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserPasswordDTO;
import com.chilluminati.chillstock.member.mypage.service.MemberMypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberMypageController {

    private final MemberMypageService memberMypageService;


    /**
     * 회원 정보 조회
     * @param model
     * @return
     */
    @GetMapping("/mypage")
    public String showMyPage( Model model) {
        UserBizDTO dto = memberMypageService.viewMyInfo();
        model.addAttribute("myInfo", dto);
        return "member/mypage";
    }

    /***
     * 회원 정보 수정
     * @param updateDTO
     * @return
     */
    @PostMapping("/update")
    public String updateMyInfo(@ModelAttribute MypageUpdateDTO updateDTO) {
        memberMypageService.updateMyInfo(updateDTO);

        return "redirect:/member/mypage";
    }

    /***
     * 회원 탈퇴
     * @return
     */
    @PostMapping("/delete")
    public String deleteAccount() {
        memberMypageService.deleteMyAccount();

        return "redirect:/nonuser"; // 탈퇴 후 로그아웃 또는 홈 이동
    }

    /***
     * 비밀번호 변경
     */
    @PostMapping("/updatepassword")
    public String updatePassword(@ModelAttribute UserPasswordDTO userPasswordDTO) {
        memberMypageService.updateMemberPassword(userPasswordDTO);
        return "redirect:/member/mypage";
    }
}
