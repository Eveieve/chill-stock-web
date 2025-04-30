package com.chilluminati.chillstock.admin.user.controller;

import com.chilluminati.chillstock.admin.user.dto.DeletedUserDTO;
import com.chilluminati.chillstock.admin.user.dto.UserBizDTO;
import com.chilluminati.chillstock.admin.user.exception.AdminUserException;
import com.chilluminati.chillstock.admin.user.service.AdminUserService;
import com.chilluminati.chillstock.admin.user.vo.UserBizBackupVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
            // 뷰로 total 회원 값 넘겨주기
            model.addAttribute("total", total);
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


        List<DeletedUserDTO> users = adminUserService.getAllDeletedByPage(page);
        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "admin/deleted-users"; // → deleted-users.html로 렌더링
    }

    /**
     * 승인 대기 회원 모두 보기
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/PENDING")
    public String getPendingUsers(@RequestParam(defaultValue = "1") int page, Model model) {
        List<UserBizDTO> users = adminUserService.getPendingUsersByPage(page);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("statusFilter", "PENDING");
//<a th:href="@{/admin/users/PENDING?page=1}">대기 중 회원 보기</a>

        return "admin/users";
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

        // 이름 검색란이 비었으면 회원목록을 모두 보여주기
        if(name.isEmpty()) return "redirect:/admin/users";
        // 검색결과 없을 경우
        if (users.isEmpty()) {
            // 검색결과가 없다는 메시지 띄우기
            model.addAttribute("NoUserFoundMessage", "사용자를 찾을 수 없습니다.");

        } else { // 모든 회원 목록 이랑 같이 페이지 사용하기 위해서 그냥 USERS
            model.addAttribute("users", users); // 검색된 사용자 보여주기
        }


        return "admin/users"; // 단순 검색(GET 요청)이고 model에 검색 결과를 담아 그대로 렌더링
    }

    /**
     * 여러 명의 회원을 일괄 삭제 처리한다
     * @param userIds 삭제할 회원 ID 목록
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteUsers(@RequestBody List<Integer> userIds) {
        Map<String, Object> result = new HashMap<>();

        try {
            adminUserService.deleteUsersByIds(userIds);
            result.put("success", true);
            result.put("message", "회원이 삭제되었습니다.");
        } catch (AdminUserException e) {
            result.put("success", false);
            result.put("message", e.getErrorCode().getMessage());
        }
        return result;
    }

    /**
     * 특정 회원의 상세 정보를 조회한다.
     * @param userId 회원 ID
     * @param model Thymeleaf에 전달할 모델
     * @return 상세 정보 페이지 경로
     */
    @GetMapping("/detail")
    public String viewUserDetail(@RequestParam("userId") Integer userId, Model model) {
        UserBizDTO user = adminUserService.viewUserDetail(userId);
        model.addAttribute("user", user);
        return "admin/user-detail";
    }

    /**
     * 여러 명의 대기 중인 회원을 일괄 승인한다
     * @param userIds 승인할 회원 ID 목록
     */
    @PostMapping("/APPROVED")
    @ResponseBody
    public Map<String, Object> approveUsers(@RequestBody List<Integer> userIds) { // 클라이언트가 전송한 요청의 바디에 담긴 제이슨 데이터를 자바 객체로 자동 변환 @RequestBody
        Map<String, Object> result = new HashMap<>();
        try {
            adminUserService.approveUsersByIds(userIds);
            result.put("success", true);
            result.put("message", "회원이 승인되었습니다.");
        } catch (AdminUserException e) {
            result.put("success", false);
            result.put("message", e.getErrorCode().getMessage());
        }
        return result;

    }


}
