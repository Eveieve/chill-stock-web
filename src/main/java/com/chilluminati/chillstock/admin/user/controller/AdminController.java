package com.chilluminati.chillstock.admin.user.controller;

import com.chilluminati.chillstock.admin.user.dto.UserBizDTO;
import com.chilluminati.chillstock.admin.user.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminUserService adminUserService;

    @GetMapping("/search-by-name")
    public String searchUserByName(@RequestParam("name") String name, Model model) {
        List<UserBizDTO> users = adminUserService.searchUserByName(name);

        if (users.isEmpty()) {
            model.addAttribute("message", "사용자를 찾을 수 없습니다.");
        } else {
            model.addAttribute("userList", users);
        }

        return "admin/user/search-result"; // 관리자의 /회워관리페이지/검색버튼 누르는 화면으로 되돌아감
    }
}
