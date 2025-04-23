package com.chilluminati.chillstock.admin.repository;

import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.exception.AdminUserException;
import com.chilluminati.chillstock.admin.user.repository.AdminUserRepo;
import com.chilluminati.chillstock.admin.user.service.AdminUserServiceImpl;
import com.chilluminati.chillstock.admin.user.vo.UserBizBackupVO;
import com.chilluminati.chillstock.admin.user.vo.UserBizVO;
import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.repository.UserRepo;
import com.chilluminati.chillstock.nonuser.service.NonUserService;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @Configuration
    static class TestTxConfig {
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }
    @Autowired
    private AdminUserServiceImpl adminUserService;

    @Autowired
    private AdminUserRepo adminUserRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private NonUserService nonUserService;

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

        nonUserService.signUp(signupDto);

        // 추가: 방금 가입한 유저를 로그인 아이디로 다시 조회해 userId 가져오기
        UserVO user = userRepo.findByLoginId(loginId);  // ← 이걸로 조회
        insertedUserId = user.getUserId();
    }

    @Test
    @DisplayName("userId로 회원과 사업자 상세 정보를 조회할 수 있다")
    void viewUserDetailTest() {
        // given
        Integer userId = 1; // DB에 존재하는 user_id를 사용하세요

        // when
        UserBizVO userBizVO = adminUserRepo.getUserBizById(userId);

        // then
        assertNotNull(userBizVO, "조회된 UserBizVO는 null이 아니어야 한다");
        assertNotNull(userBizVO.getUserLoginId(), "userLoginId는 null이 아니어야 한다");
        assertNotNull(userBizVO.getUserName(), "userName은 null이 아니어야 한다");
        assertNotNull(userBizVO.getBusinessName(), "businessName은 null이 아니어야 한다");

    }

    @Test
    @DisplayName("중복 이름을 가진 회원의 회원+사업자 정보를 모두 조회할 수 있다")
    void searchUsersByName_중복이름조회_테스트() {
        // given
        String name = "서비스비밀번호변경";

        // when
        List<UserBizVO> users = adminUserRepo.getUsersByName(name);

        // then
        // 조회한 리스트가
        Assertions.assertNotNull(users);

        // 리스트의 모든 회원이 기대하는 name 과 ㅇ리치하는지 확인
        for (UserBizVO user : users) {
            assertEquals(name, user.getUserName());
        }
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
        assertEquals(UserStatus.APPROVED, approvedUser.getUserStatus());
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

    @DisplayName("여러 명의 회원을 삭제할 수 있다.")
    @Test
    @Transactional
    void deleteUsersByIds_여러회원삭제_테스트() {
        // given
        List<Integer> userIds = Arrays.asList(2, 3); // 실제 존재하는 user_id 사용

        // when
        int deletedCount = adminUserRepo.deleteUsersByIds(userIds);

        // then
        Assertions.assertTrue(deletedCount > 0, "삭제된 행 수는 0보다 커야 한다");
    }


}

