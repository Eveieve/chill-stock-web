package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.config.AppConfig;
import com.chilluminati.chillstock.config.HikariCPConfig;
import com.chilluminati.chillstock.config.MybatisConfig;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
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

@ContextConfiguration(classes = {
        AppConfig.class,
        MybatisConfig.class,
        HikariCPConfig.class  // DB 연동용 설정
})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BizRepo bizRepo;

    @Autowired
    private ModelMapper modelMapper;

    // BizVO의 setUserId 를 mybatis 가 자동매핑 하지 못하도록 설정
    // userId는 반드시 User 테이블에서 insert된 이후에 설정되야 함.
    // ModelMapper가 먼저 null로 세팅하면 안됨.
    @BeforeEach
    void setUp() {
        modelMapper.typeMap(SignUpDTO.class, BizVO.class)
                .addMappings(mapper -> mapper.skip(BizVO::setUserId));
    }

    @Test
    @DisplayName("회원가입 시 회원정보와 사업체정보가 함께 저장된다.")
    void signUpWithBusinessInfoTest() {
        // given
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String loginId = "chillstock_" + uniqueId;
        String businessRegistNum = "555-" + uniqueId.substring(4, 6) + "-" + uniqueId.substring(6, 11);

        SignUpDTO signupDto = SignUpDTO.builder()
                .userLoginId(loginId)
                .userPassword("chillstock1234")
                .userEmail("chillstock04@example.com")
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
        Assertions.assertEquals("칠스탁", savedUser.getUserName());

        // 사업체 정보 별도 조회
        BizVO savedBiz = bizRepo.findByUserId(savedUser.getUserId());
        Assertions.assertNotNull(savedBiz);
        Assertions.assertEquals("칠스탁상점", savedBiz.getBusinessName());
        Assertions.assertEquals("서울특별시 강남구 칠스탁타운", savedBiz.getBusinessAddress());
        Assertions.assertEquals(savedUser.getUserId(), savedBiz.getUserId()); // 외래키로 연결되었는지 확인
    }

}