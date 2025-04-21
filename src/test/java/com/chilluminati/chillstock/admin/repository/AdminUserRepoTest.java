package com.chilluminati.chillstock.admin.repository;

import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.exception.AdminUserException;
import com.chilluminati.chillstock.admin.user.repository.AdminUserRepo;
import com.chilluminati.chillstock.admin.user.service.AdminUserServiceImpl;
import com.chilluminati.chillstock.admin.user.vo.UserBizBackupVO;
import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.repository.UserRepo;
import com.chilluminati.chillstock.nonuser.service.UserService;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * 관리자가 할 수 있는 기능을 테스트 한다
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class
})
class AdminUserRepoTest {

    @Autowired
    private AdminUserServiceImpl adminUserService;

    @Autowired
    private AdminUserRepo adminUserRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    private Integer insertedUserId;

    @BeforeEach
    void setUp() {
        // given
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String loginId = "chillstock_" + uniqueId;
        String email = "updatepw_service_" + uniqueId + "@example.com";
        String businessRegistNum = "888-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);

        SignUpDTO signupDto = SignUpDTO.builder()
                .userLoginId(loginId)
                .userPassword("originalPassword123!")
                .userPasswordCheck("originalPassword123!")
                .userEmail(email)
                .userName("서비스비밀번호변경")
                .userPhone("010-5678-9999")
                .businessRegistNum(businessRegistNum)
                .businessName("서비스비번상점")
                .businessAddress("서울시 서비스구")
                .businessPost("54321")
                .build();

        userService.signUp(signupDto);

        // 추가: 방금 가입한 유저를 로그인 아이디로 다시 조회해 userId 가져오기
        UserVO user = userRepo.findByLoginId(loginId);  // ← 이걸로 조회
        insertedUserId = user.getUserId();
    }

    @Test
    @DisplayName("회원 한 명 삭제 성공 - 실제 삭제 확인")
    void deleteUserById () {
        // when
        adminUserService.deleteUserById(insertedUserId);

        // then
        UserVO deletedUser = userRepo.findByUserId(insertedUserId); //nonuserRepo로 테스트함
        Assertions.assertNull(deletedUser, "삭제된 회원은 더 이상 조회되지 않아야 한다");
    }

    @Test
    @DisplayName("회원 한명 승인 성공")
    void approveUserById() {
        // when
        adminUserService.approveUserById(insertedUserId);

        // then
        UserVO approvedUser = userRepo.findByUserId(insertedUserId);
        Assertions.assertEquals(UserStatus.APPROVED, approvedUser.getUserStatus());
    }

    // 회원 삭제시 사업체 정보와 함께 백업 테이블에 들어간다

    @Test
    @DisplayName("회원 삭제 실패 시 예외 발생")
    void deleteUser_fail() {
        // when & then
        Assertions.assertThrows(AdminUserException.class, () -> {
            adminUserService.deleteUserById(99999); // 존재하지 않는 ID
        });
    }

    @Test
    @DisplayName("회원 승인 실패 시 예외 발생")
    void approveUser_fail() {
        // when & then
        Assertions.assertThrows(AdminUserException.class, () -> {
            adminUserService.approveUserById(99999); // 존재하지 않는 ID
        });
    }
@Test
@DisplayName("회원 계정 삭제 후, 탈퇴 백업 테이블에서 회원+사업체 정보가 조회된다.")
void getAllDeletedUsers() {
    // given 회원을 지우면
    adminUserService.deleteUserById(insertedUserId);

    // when 모든 탈퇴회원 불러오기 버튼 누르면
    List<UserBizBackupVO> deletedUsers = adminUserService.getAllDeletedUsers();

    // then
    boolean match = false;
    for (UserBizBackupVO user : deletedUsers) {
        if ("서비스비밀번호변경".equals(user.getUserName()) &&
                "서비스비번상점".equals(user.getBusinessName())) {
            match = true;
            break;
        }
    }
    Assertions.assertTrue(match, "삭제된 회원의 정보가 백업 리스트에 포함되어야 한다");
}

}

