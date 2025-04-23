package com.chilluminati.chillstock.admin.user.controller;

import com.chilluminati.chillstock.admin.user.dto.UserBizDTO;
import com.chilluminati.chillstock.admin.user.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class AdminController {
    private final AdminUserService adminUserService;

    @GetMapping("/admin/users")
    public String getAllUsers(@RequestParam(defaultValue = "1") int page, Model model) {
        List<UserBizDTO> users = adminUserService.getAllUsersByPage(page);
        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        return "admin/user-list";
    }

    /**
     * 이름으로 회원(들)을 검색한다
     * @param name
     * @param model
     * @return
     */
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
    /**
     * 여러 명의 회원을 일괄 삭제 처리한다
     * @param userIds 삭제할 회원 ID 목록
     */
    @PostMapping("/bulk-delete")
    public void deleteUsers(@RequestBody List<Integer> userIds) {
        adminUserService.deleteUsersByIds(userIds);

        // 페이지 이동이 필요한가?
    }

    /**
     * 여러 명의 대기 중인 회원을 일괄 승인한다
     * @param userIds 승인할 회원 ID 목록
     */
    @PostMapping("/bulk-approve")
    public void approveUsers(@RequestBody List<Integer> userIds) {
        adminUserService.approveUsersByIds(userIds);

        // 페이지 이동이나 응답이 필요하면 여기서 추가
    }
}
