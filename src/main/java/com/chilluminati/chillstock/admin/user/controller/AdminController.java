package com.chilluminati.chillstock.admin.user.controller;

import com.chilluminati.chillstock.admin.user.dto.DeletedUserDTO;
import com.chilluminati.chillstock.admin.user.dto.UserBizDTO;
import com.chilluminati.chillstock.admin.user.exception.AdminUserException;
import com.chilluminati.chillstock.admin.user.service.AdminUserService;
import com.chilluminati.chillstock.admin.user.vo.UserBizBackupVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminUserService adminUserService;

    /**
     * 모든 회원 조회하기
     * @param page
     * @param model
     * @return
     */
        @GetMapping("/users")
        public String getAllUsers(@RequestParam(defaultValue = "1") int page, Model model) {

            int pageSize = 10;

            // 전체 회원 수를 정확히 구하는 메서드
            int total = adminUserService.countAllUsers();
            int totalPages = (int) Math.ceil((double) total / pageSize);

            if (page < 1) page = 1;
            if (page > totalPages) page = totalPages;
            List<UserBizDTO> users = adminUserService.getAllUsersByPage(page);
            model.addAttribute("users", users);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            return "admin/users";
        }

    /**
     * 탈퇴된 회원 전체 조회
     * @param model Thymeleaf에 넘길 데이터
     * @return 탈퇴회원 목록 페이지
     */
    @GetMapping("/deleted-users")
    public String showDeletedUsers(@RequestParam(defaultValue = "1") int page, Model model) {

        int pageSize = 10;

        int total = adminUserService.countAllDeleted();
        int totalPages = (int) Math.ceil((double) total / pageSize);

        if (page < 1) page = 1;
        if (page > totalPages) page = totalPages;


        List<DeletedUserDTO> deletedUsers = adminUserService.getAllDeletedByPage(page);
        model.addAttribute("deletedUsers", deletedUsers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "admin/deleted-users"; // → deleted-users.html로 렌더링
    }

    /**
     * 이름으로 회원(들)을 검색한다
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String searchUserByName(@RequestParam("name") String name, Model model) {
        List<UserBizDTO> users = adminUserService.searchUserByName(name);

        if (users.isEmpty()) {
            model.addAttribute("NoUserFoundMessage", "사용자를 찾을 수 없습니다.");
        } else {
            model.addAttribute("userListByName", users);
        }

        return "/users"; // 단순 검색(GET 요청)이고 model에 검색 결과를 담아 그대로 렌더링
    }

    /**
     * 여러 명의 회원을 일괄 삭제 처리한다
     * @param userIds 삭제할 회원 ID 목록
     */
    @PostMapping("/delete")
    public String deleteUsers(@RequestBody List<Integer> userIds, RedirectAttributes redirectAttributes) {
        try {
            adminUserService.deleteUsersByIds(userIds);
            redirectAttributes.addFlashAttribute("deleteSuccessMessage", "삭제 처리가 완료되었습니다.");
        } catch (AdminUserException e) {
            redirectAttributes.addFlashAttribute("message", e.getErrorCode().getMessage());
        }

        return "redirect:/admin/users";
    }

    /**
     * 여러 명의 대기 중인 회원을 일괄 승인한다
     * @param userIds 승인할 회원 ID 목록
     */
    @PostMapping("/approve")
    public String approveUsers(@RequestBody List<Integer> userIds, RedirectAttributes redirectAttributes) { // 클라이언트가 전송한 요청의 바디에 담긴 제이슨 데이터를 자바 객체로 자동 변환 @RequestBody
        // 1. 승인하기
        try {
            adminUserService.approveUsersByIds(userIds);
            // 2. 완료 메시지 전달 (redirect 시에도 메시지 유지)
            redirectAttributes.addFlashAttribute("approvalSuccessMessage", "승인 처리가 완료되었습니다.");
        } catch (AdminUserException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getErrorCode().getMessage());
        }
//<!-- admin/users.html -->
//<div th:if="${successMessage}" class="alert alert-success" th:text="${approvalSuccessMessage}"></div>
        // 3. 같은 페이지로 리다이렉트
        return "redirect:/admin/users";

    }
}
