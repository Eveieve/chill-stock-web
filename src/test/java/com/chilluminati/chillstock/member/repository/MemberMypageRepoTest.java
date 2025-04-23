package com.chilluminati.chillstock.member.repository;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.member.mypage.dto.MypageUpdateDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserBizDTO;
import com.chilluminati.chillstock.member.mypage.dto.UserPasswordDTO;
import com.chilluminati.chillstock.member.mypage.service.MemberMypageService;

import com.chilluminati.chillstock.nonuser.repository.UserRepo;
import com.chilluminati.chillstock.nonuser.service.NonUserService;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import com.chilluminati.chillstock.security.EmailUserDetails;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

/**
 * 회원 마이페이지 기능 중 회원 탈퇴 테스트
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class,
        MemberMypageRepoTest.TestTxConfig.class
})
class MemberMypageRepoTest {
    //transactional을 하기 위해 세팅.. transactionManager를 내부구현 해놓은 config가 없어서 이렇게 일단 세팅함
    @Configuration
    static class TestTxConfig {
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

    @Autowired
    private MemberMypageService memberMypageService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private NonUserService nonUserService;

    /**
     * 전체 실행 전에 로그인하기
     */
    @BeforeAll
    static void loginMember() {
        String loginId = "mypage_1745226903096";
        String password = "mypageTest123";
        String role = "ROLE_member";
        int pk = 22;

        // id: mypage_1745226903096
        // pw: mypageTest123!
        // 22

        // 테스트용 사용자 정보 생성
        EmailUserDetails emailUserDetails = new EmailUserDetails();

        emailUserDetails.setUserId(pk);
        emailUserDetails.setUserLoginId(loginId);
        emailUserDetails.setUserPassword(password);
        emailUserDetails.setUserType("member");

        // 로그인시키기
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(emailUserDetails, password, List.of(new SimpleGrantedAuthority(role)));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    @DisplayName("회원과 사업체 정보를 각각 조회하고 DTO로 반환할 수 있다.")
    void viewMyInfoTest() {
        // given
        //현재 로그인된 사용자
        EmailUserDetails emailUserDetails = getEmailUserDetails();
        String userLoginId = emailUserDetails.getUserLoginId();

        // when
        //현재 로그인된 사용자id로 조회한 유저
        UserBizDTO dto = memberMypageService.viewMyInfo();

        // then
        Assertions.assertEquals(userLoginId, dto.getUserLoginId());
//        Assertions.assertEquals(email, dto.getUserEmail());
//        Assertions.assertEquals("마이페이지상점", dto.getBusinessName());
//        Assertions.assertEquals(businessRegistNum, dto.getBusinessRegistNum());
    }

    @Test
    @DisplayName("회원이 직접 마이페이지에서 회원 탈퇴 요청을 하면 실제로 삭제된다.")
    @Transactional
    void deleteMemberTest() {
        // given
        EmailUserDetails emailUserDetails = getEmailUserDetails();
        Integer userId = emailUserDetails.getUserId();

        // when
        memberMypageService.deleteMyAccount();

        // then
        UserVO deletedUser = userRepo.findByUserId(userId);
        Assertions.assertNull(deletedUser, "탈퇴한 회원은 더 이상 조회되지 않아야 한다");
    }

    @Test
    @DisplayName("마이페이지에서 회원의 패스워드를 변경하면 변경이 된다.")
    @Transactional
    void updateMemberPasswordTest() {
        //given Origin
        EmailUserDetails emailUserDetails = getEmailUserDetails();
        Integer userId = emailUserDetails.getUserId();

        UserPasswordDTO passwordDTO = new UserPasswordDTO();
        passwordDTO.setUserPassword("1234"); //비밀번호는 1234

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // BCrypt 인코더 (암호화하거나 비교만 가능한 클래스)

        //when
        memberMypageService.updateMemberPassword(passwordDTO); // 1234로 업데이트 (암호화되서 저장됨)

        //then
        UserVO changedUser = userRepo.findByUserId(userId); // user 찾기
        String userPassword = changedUser.getUserPassword(); // 비번 찾기 (암호화된상태)

        boolean matches = encoder.matches("1234", userPassword); // 인코더로 1234와 암호문을 대조함
        Assertions.assertEquals(true, matches); // 결과값이 true여야 같다는 뜻이므로 true와 matches를 대조함
    }

    @Test
    @DisplayName("마이페이지에서 회원의 정보를 수정하면 수정이 된다.")
    @Transactional
    void updateMemberTest() {
        //given
        EmailUserDetails emailUserDetails = getEmailUserDetails();
        Integer userId = emailUserDetails.getUserId();

        MypageUpdateDTO mypageUpdateDTO = new MypageUpdateDTO();
        mypageUpdateDTO.setUserName("hi");
        mypageUpdateDTO.setUserPhone("01012341234");
        mypageUpdateDTO.setUserEmail("test@test.com");

        mypageUpdateDTO.setBusinessAddress("address");
        mypageUpdateDTO.setBusinessPost("40404");
        mypageUpdateDTO.setBusinessName("test");
        mypageUpdateDTO.setBusinessRegistNum("23423");

        //when
        memberMypageService.updateMyInfo(mypageUpdateDTO);

        //then
        UserVO changedUser = userRepo.findByUserId(userId);
        Assertions.assertEquals("hi", changedUser.getUserName());
    }

    /***
     * get UserVO
     * @return
     */
    private EmailUserDetails getEmailUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (EmailUserDetails) authentication.getPrincipal(); // extract userVO
    }
}
