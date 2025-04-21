package com.chilluminati.chillstock.member.repository;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.member.mypage.dto.MypageUpdateDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserBizDTO;
import com.chilluminati.chillstock.member.mypage.service.MemberMypageService;

import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.repository.UserRepo;
import com.chilluminati.chillstock.nonuser.service.UserService;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 회원 마이페이지 기능 중 회원 탈퇴 테스트
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class
})
class MemberMypageRepoTest {

    @Autowired
    private MemberMypageService memberMypageService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    private Integer insertedUserId;

    @BeforeEach
    void setUp() {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String loginId = "mypage_" + uniqueId;
        String email = "mypage_" + uniqueId + "@example.com";
        String businessRegistNum = "777-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);

        SignUpDTO signupDto = SignUpDTO.builder()
                .userLoginId(loginId)
                .userPassword("mypageTest123!")
                .userPasswordCheck("mypageTest123!")
                .userEmail(email)
                .userName("마이페이지탈퇴")
                .userPhone("010-9999-7777")
                .businessRegistNum(businessRegistNum)
                .businessName("마이페이지상점")
                .businessAddress("서울시 마포구 마이페이지")
                .businessPost("11111")
                .build();

        userService.signUp(signupDto);
        UserVO user = userRepo.findByLoginId(loginId);
        insertedUserId = user.getUserId();
    }

//    @Test
//    @DisplayName("회원과 사업체 정보를 각각 조회하고 DTO로 반환할 수 있다.")
//    void viewMyInfoTest() {
//        // when
//        UserBizDTO dto = memberMypageService.viewMyInfo(insertedUserId);
//
//        // then
//        Assertions.assertNotNull(dto);
//        Assertions.assertEquals("마이페이지탈퇴", dto.getUserName());
//        Assertions.assertEquals("010-9999-7777", dto.getUserPhone());
//        Assertions.assertEquals("마이페이지상점", dto.getBusinessName());
//        Assertions.assertEquals("서울시 마포구 마이페이지", dto.getBusinessAddress());
//    }

    @Test
    @DisplayName("회원과 사업체 정보를 각각 조회하고 DTO로 반환할 수 있다.")
    void viewMyInfoTest() {
        // given
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String loginId = "mypage_" + uniqueId;
        String email = loginId + "@example.com";
        String businessRegistNum = "777-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);

        SignUpDTO signUpDTO = SignUpDTO.builder()
                .userLoginId(loginId)
                .userPassword("mypageTest123!")
                .userPasswordCheck("mypageTest123!")
                .userEmail(email)
                .userName("마이페이지탈퇴")
                .userPhone("010-9999-7777")
                .businessName("마이페이지상점")
                .businessRegistNum(businessRegistNum)
                .businessAddress("서울시 마포구 마이페이지")
                .businessPost("11111")
                .build();

        // 회원가입
        userService.signUp(signUpDTO);

        // userId 조회
//        UserVO insertedUser = userRepo.findByLoginId(signUpDTO.getUserLoginId());
//        Integer userId = insertedUser.getUserId();

        // when
        UserBizDTO dto = memberMypageService.viewMyInfo();

        // then
        Assertions.assertEquals(loginId, dto.getUserLoginId());
        Assertions.assertEquals(email, dto.getUserEmail());
        Assertions.assertEquals("마이페이지상점", dto.getBusinessName());
        Assertions.assertEquals(businessRegistNum, dto.getBusinessRegistNum());
    }

    @Test
    @DisplayName("회원이 직접 마이페이지에서 회원 탈퇴 요청을 하면 실제로 삭제된다.")
    void deleteMemberTest() {
        // when
        memberMypageService.deleteMyAccount();

        // then
        UserVO deletedUser = userRepo.findByUserId(insertedUserId);
        Assertions.assertNull(deletedUser, "탈퇴한 회원은 더 이상 조회되지 않아야 한다");
    }
}
