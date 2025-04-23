package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.common.UserType;
import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.nonuser.dto.EmailDupDTO;
import com.chilluminati.chillstock.nonuser.dto.LoginIdDupDTO;
import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.exception.SignUpException;
import com.chilluminati.chillstock.nonuser.repository.BizRepo;
import com.chilluminati.chillstock.nonuser.repository.UserRepo;
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

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class  // DB 연동용 설정
})
@ExtendWith(SpringExtension.class)
public class NonUserServiceImplTest {
    @Autowired
    private NonUserServiceImpl userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BizRepo bizRepo;


    // BizVO의 setUserId 를 mybatis 가 자동매핑 하지 못하도록 설정
    // userId는 반드시 User 테이블에서 insert된 이후에 설정되야 함.
    // ModelMapper가 먼저 null로 세팅하면 안됨.
    @BeforeEach
    void setUp() {
        modelMapper.typeMap(SignUpDTO.class, BizVO.class)
                .addMappings(mapper -> mapper.skip(BizVO::setUserId));
    }

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void 이메일_중복확인_테스트() {
        userRepo.insertUser(UserVO.builder()
                .userLoginId("emailDupUser")
                .userEmail("dupemail@test.com")
                .userPassword("pw1234")
                .userName("이메일중복확인용")
                .userPhone("010-1234-5678")
                .userType(UserType.valueOf("ROLE_member"))
                .userStatus(UserStatus.valueOf("pending"))
                .build());

        EmailDupDTO emailDupDto = EmailDupDTO.builder()
                .userEmail("dupemail@test.com")
                .build();

        boolean result = userService.checkEmailDuplicate(emailDupDto);
        assertTrue(result);
    }

    @Test
    void 아이디_중복확인_테스트() {
        userRepo.insertUser(UserVO.builder()
                .userLoginId("duplicateUser")
                .userEmail("dup@test.com")
                .userPassword("pw1234")
                .userName("중복확인용")
                .userPhone("010-0000-1111")
                .userType(UserType.valueOf("ROLE_member"))
                .userStatus(UserStatus.valueOf("pending"))
                .build());

        LoginIdDupDTO loginIdDto = LoginIdDupDTO.builder()
                .userLoginId("duplicateUser")
                .build();

        boolean result = userService.checkLoginIdDuplicate(loginIdDto);
        assertTrue(result);
    }

    @Test
    @DisplayName("회원가입 시 회원정보와 사업체정보가 함께 저장된다.")
    void signUpWithBusinessInfoTest() {


        // given
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String loginId = "chillstock_" + uniqueId;
        String businessRegistNum = "555-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);
        String email = "test_" + System.currentTimeMillis() + "@example.com";

        SignUpDTO signupDto = SignUpDTO.builder()
                .userLoginId(loginId)
                .userPassword("chillstock1234")
                .userPasswordCheck("chillstock1234")
                .userEmail(email)
                .userName("칠스탁")
                .userPhone("010-1234-5678")
                .businessRegistNum(businessRegistNum)
                .businessName("칠스탁상점")
                .businessAddress("서울특별시 강남구 칠스탁타운")
                .businessPost("12371")
                .build();

        // when
        userService.signUp(signupDto);

        // then
        UserVO savedUser = userRepo.findByLoginId(loginId);
        Assertions.assertNotNull(savedUser);
        assertEquals("칠스탁", savedUser.getUserName());

        // 사업체 정보 별도 조회
        BizVO savedBiz = bizRepo.findByUserId(savedUser.getUserId());
        Assertions.assertNotNull(savedBiz);
        assertEquals("칠스탁상점", savedBiz.getBusinessName());
        assertEquals("서울특별시 강남구 칠스탁타운", savedBiz.getBusinessAddress());
        assertEquals(savedUser.getUserId(), savedBiz.getUserId()); // 외래키로 연결되었는지 확인

        System.out.println("생성된 userId = " + savedUser.getUserId());
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
        assertEquals(newPassword, updatedUser.getUserPassword());
    }
    @Test
    void 비밀번호_불일치_회원가입_예외발생() {
        SignUpDTO dto = SignUpDTO.builder()
                .userLoginId("failuser")
                .userPassword("pw1")
                .userPasswordCheck("pw2")
                .build();

        assertThrows(SignUpException.class, () -> userService.signUp(dto));
    }

    @Test
    void 이메일로_아이디_조회_테스트() {
        userRepo.insertUser(UserVO.builder()
                .userLoginId("emailuser")
                .userEmail("email@test.com")
                .userPassword("pw")
                .userName("이메일유저")
                .userPhone("010")
                .userType(UserType.valueOf("ROLE_member"))
                .userStatus(UserStatus.valueOf("pending"))
                .build());

        String loginId = userService.findLoginIdByEmail("email@test.com");
        assertEquals("emailuser", loginId);
    }


}
