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

    // 회원 한명 회원가입 시키기
    private Integer registerTestUser(String tag) {
        String uniqueId = tag + "_" + System.currentTimeMillis();
        String loginId = uniqueId;
        String email = uniqueId + "@example.com";
        String businessRegistNum = "999-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);

        SignUpDTO dto = SignUpDTO.builder()
                .userLoginId(loginId)
                .userPassword("test1234!")
                .userPasswordCheck("test1234!")
                .userEmail(email)
                .userName("삭제용회원")
                .userPhone("010-0000-0000")
                .businessRegistNum(businessRegistNum)
                .businessName("삭제상점")
                .businessAddress("서울시 테스트구")
                .businessPost("12345")
                .build();

        nonUserService.signUp(dto);
        return userRepo.findByLoginId(loginId).getUserId();
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
@DisplayName("회원 계정 삭제 후, 탈퇴 백업 테이블에서 회원+사업체 정보가 조회된다.")
void getAllDeletedUsers() {
    // given
    Integer userId = registerTestUser("test");
    Integer userId2 = registerTestUser("test2");
    List<Integer> userIds = Arrays.asList(userId, userId2); // 실제 존재하는 user_id 사용
    adminUserRepo.deleteUsersByIds(userIds);

}

    @DisplayName("여러 명의 회원을 삭제할 수 있다.")
    @Test
    void deleteUsersByIds() {

        Integer userId = registerTestUser("test");
        Integer userId2 = registerTestUser("test2");
        // given
        List<Integer> userIds = Arrays.asList(userId, userId2); // 실제 존재하는 user_id 사용

        // when
        int deletedCount = adminUserRepo.deleteUsersByIds(userIds);

        // then
        Assertions.assertTrue(deletedCount > 0, "삭제된 행 수는 0보다 커야 한다");
    }
    @DisplayName("여러 명의 회원을 승인 할 수 있다.")
    @Test
    @Transactional
    void approveUsersByIds() {
        // given
        Integer userId1 = registerTestUser("test13");  // DB에 유저 삽입
        Integer userId2 = registerTestUser("test14");

        List<Integer> userIds = Arrays.asList(userId1, userId2);

        // when
        adminUserService.approveUsersByIds(userIds);

        // then
        Assertions.assertEquals(UserStatus.approve, userRepo.findByUserId(userId1).getUserStatus());
        Assertions.assertEquals(UserStatus.approve, userRepo.findByUserId(userId2).getUserStatus());
    }
}

