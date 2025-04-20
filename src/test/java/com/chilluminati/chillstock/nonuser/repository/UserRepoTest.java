package com.chilluminati.chillstock.nonuser.repository;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.service.UserServiceImpl;
import com.chilluminati.chillstock.nonuser.vo.BizVO;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class  // DB 연동용 설정
})
@ExtendWith(SpringExtension.class)
class UserRepoTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BizRepo bizRepo;

    @Autowired
    private ModelMapper modelMapper;



    @Test
    @DisplayName("회원정보와 사업체정보를 직접 insert한 후 조인된 결과를 조회할 수 있다.")
    void insertAndJoinTest() {
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


}