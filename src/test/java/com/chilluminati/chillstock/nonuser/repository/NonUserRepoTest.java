package com.chilluminati.chillstock.nonuser.repository;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.service.NonUserServiceImpl;
import com.chilluminati.chillstock.nonuser.vo.BizVO;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class  // DB 연동용 설정
})
@ExtendWith(SpringExtension.class)
class AdminUserRepoTest {

    @Autowired
    private NonUserServiceImpl userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BizRepo bizRepo;


    @Test
    @DisplayName("이미 존재하는 이메일인지 확인할 수 있다")
    void existsByEmailTest() {
        // given
        String existingEmail = "mypage_1745226481031@example.com"; // 실제 DB에 존재하는 이메일

        // when
        boolean exists = userRepo.existsByEmail(existingEmail);

        // then
        Assertions.assertTrue(exists, "DB에 이미 존재하는 이메일이면 true를 반환해야 한다");
    }


    @Test
    @DisplayName("로그인 아이디가 이미 존재하는지 확인할 수 있다")
    void existsByLoginIdTest() {
        // given
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String loginId = "testLogin_" + uniqueId;
        String email = "exists_" + uniqueId + "@example.com";
        String businessRegistNum = "123-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);

        SignUpDTO signUpDTO = SignUpDTO.builder()
                .userLoginId(loginId)
                .userPassword("password1234!")
                .userPasswordCheck("password1234!")
                .userEmail(email)
                .userName("존재테스트")
                .userPhone("010-1111-2222")
                .businessRegistNum(businessRegistNum)
                .businessName("존재상점")
                .businessAddress("서울시 존재구")
                .businessPost("12345")
                .build();

        userService.signUp(signUpDTO);

        // when
        boolean exists = userRepo.existsByLoginId(loginId);

        // then
        Assertions.assertTrue(exists, "회원가입한 로그인 아이디가 존재해야 한다");
    }


    @Test
    @DisplayName("회원가입시 사용자와 사업체 정보가 함께 저장된다")
    void signUpTest() {
        // given
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String loginId = "repoTest_" + uniqueId;
        String email = "repo_" + uniqueId + "@example.com";
        String businessRegistNum = "999-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);

        UserVO user = UserVO.builder()
                .userLoginId(loginId)
                .userPassword("repoTest1234")
                .userName("레포테스터")
                .userEmail(email)
                .userPhone("010-9999-8888")
                .build();

        // when
        userRepo.insertUser(user);
        Integer userId = user.getUserId(); // insert 후 자동 세팅되는지 확인

        BizVO biz = BizVO.builder()
                .businessRegistNum(businessRegistNum)
                .businessName("레포상점")
                .businessAddress("서울특별시 테스트구 레포로")
                .businessPost("11111")
                .userId(userId) // 외래키 연결
                .build();

        bizRepo.insertBiz(biz);

        // then
        UserVO result = userRepo.findByLoginId(loginId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("레포테스터", result.getUserName());
        Assertions.assertEquals(email, result.getUserEmail());

        BizVO resultBiz = result.getBizVO();
        Assertions.assertNotNull(resultBiz);
        Assertions.assertEquals("레포상점", resultBiz.getBusinessName());
        Assertions.assertEquals("서울특별시 테스트구 레포로", resultBiz.getBusinessAddress());
    }

    @Test
    @DisplayName("이메일로 회원정보와 사업체정보를 함께 조회할 수 있다.")
    void findByEmailTest() {
        // given
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String loginId = "chillstock_" + uniqueId;
        String email = "findbyemail_" + uniqueId + "@example.com";
        String businessRegistNum = "666-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);

        SignUpDTO signupDto = SignUpDTO.builder()
                .userLoginId(loginId)
                .userPassword("chillstock1234")
                .userPasswordCheck("chillstock1234")
                .userEmail(email)
                .userName("이메일로찾기")
                .userPhone("010-5678-1234")
                .businessRegistNum(businessRegistNum)
                .businessName("이메일상점")
                .businessAddress("서울특별시 마포구 이메일거리")
                .businessPost("04567")
                .build();

        userService.signUp(signupDto);

        // when
        UserVO result = userRepo.findByEmail(email);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(email, result.getUserEmail());
        Assertions.assertEquals("이메일로찾기", result.getUserName());

        BizVO biz = result.getBizVO();
        Assertions.assertNotNull(biz, "사업체 정보가 null이면 안 됩니다.");
        Assertions.assertEquals("이메일상점", biz.getBusinessName());
        Assertions.assertEquals("서울특별시 마포구 이메일거리", biz.getBusinessAddress());
    }

    @Test
    @DisplayName("사용자의 비밀번호를 성공적으로 업데이트할 수 있다.")
    void updatePasswordTest() {
        // given
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String loginId = "chillstock_" + uniqueId;
        String email = "updatepw_" + uniqueId + "@example.com";
        String businessRegistNum = "777-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);

        SignUpDTO signupDto = SignUpDTO.builder()
                .userLoginId(loginId)
                .userPassword("originalPassword123!")
                .userPasswordCheck("originalPassword123!")
                .userEmail(email)
                .userName("비밀번호변경")
                .userPhone("010-9876-5432")
                .businessRegistNum(businessRegistNum)
                .businessName("비밀번호상점")
                .businessAddress("서울특별시 비밀번호구")
                .businessPost("12345")
                .build();

        userService.signUp(signupDto);

        // when
        String newPassword = "newPassword456!";
        userRepo.updatePassword(newPassword, loginId);
        UserVO updatedUser = userRepo.findByLoginId(loginId);

        // then
        Assertions.assertEquals(newPassword, updatedUser.getUserPassword());


    }

@Test
    @DisplayName("이메일로 로그인 아이디를 찾을 수 있다.")
    void getLoginIdByEmailTest() {

    // given
    String uniqueId = String.valueOf(System.currentTimeMillis());
    String loginId = "testuser_" + uniqueId;
    String email = "findid_" + uniqueId + "@example.com";
    String businessRegistNum = "123-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);

    SignUpDTO signUpDTO = SignUpDTO.builder()
            .userLoginId(loginId)
            .userPassword("chillstock123!")
            .userPasswordCheck("chillstock123!")
            .userEmail(email)
            .userName("이메일조회")
            .userPhone("010-2222-3333")
            .businessRegistNum(businessRegistNum)
            .businessName("이메일상점")
            .businessAddress("서울시 테스트구")
            .businessPost("12345")
            .build();

    userService.signUp(signUpDTO);

    // when
    String foundLoginId = userRepo.findLoginId(email);

    // then
    Assertions.assertEquals(loginId, foundLoginId);
}
}
