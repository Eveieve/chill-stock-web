package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.nonuser.dto.EmailDupDTO;
import com.chilluminati.chillstock.nonuser.dto.LoginIdDupDTO;
import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.exception.SignUpErrorCode;
import com.chilluminati.chillstock.nonuser.exception.SignUpException;
import com.chilluminati.chillstock.nonuser.repository.BizRepo;
import com.chilluminati.chillstock.nonuser.repository.UserRepo;
import com.chilluminati.chillstock.nonuser.vo.BizVO;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import com.chilluminati.chillstock.security.encryption.Encrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class NonUserServiceImpl implements NonUserService {

    private final UserRepo userRepo;
    private final BizRepo bizRepo;
    private final ModelMapper modelMapper;
    private final Encrypt encrypt;

    @Override
    public boolean checkEmailDuplicate(EmailDupDTO dto) {
        log.error("Checking email duplicate");
        log.error("Email duplicated: {}", dto);
        boolean isDuplicate = userRepo.existsByEmail(dto.getUserEmail());

       return isDuplicate;

    }


    @Override
    public boolean checkLoginIdDuplicate(LoginIdDupDTO loginIdDupDTO) {
        return userRepo.existsByLoginId(loginIdDupDTO.getUserLoginId());
    }


    @Override
    public boolean existsByLoginId(String loginId) {
        boolean userExists = userRepo.existsByLoginId(loginId);
        return userExists;
    }

    @Override
    public String findLoginIdByEmail(String email) {
        UserVO user = userRepo.findByEmail(email);
        if (user == null) {
            throw new SignUpException(SignUpErrorCode.USER_NOT_FOUND_BY_EMAIL);
        } else {
            return user.getUserLoginId();
        }
    }

    @Override
    @Transactional
    public void signUp(SignUpDTO signupDto) {

        modelMapper.typeMap(SignUpDTO.class, BizVO.class)
                .addMappings(mapper -> mapper.skip(BizVO::setUserId));

        // 1. 비밀번호 확인 일치 여부
        if (!signupDto.getUserPassword().equals(signupDto.getUserPasswordCheck())) {
            throw new SignUpException(SignUpErrorCode.PASSWORD_MISMATCH);
        }
        try {
            // 비밀번호 암호화

            // 암호화한 비밀번호 디티오에 세팅
            signupDto.setUserPassword(encrypt.encryptPassword(signupDto.getUserPassword()));

            log.debug("#########################");
            log.debug("userPassword: {}", signupDto.getUserPassword());
            // 유저 정보만 가진  vo로 변환
            UserVO userVo = modelMapper.map(signupDto, UserVO.class);
            userRepo.insertUser(userVo);  // 이때 userId가 생성됨
            // 사업체 정보만 가진 vo 로 변환
            BizVO bizVO = modelMapper.map(signupDto, BizVO.class);
            // 사업체 정보와 유저 정보를 userId 외래키로 연결
            bizVO.setUserId(userVo.getUserId());  // 명시적으로 userId 세팅
            bizRepo.insertBiz(bizVO);  // 이제는 userId가 null이 아님
            System.out.println(bizVO.getUserId());

            log.info("##############Sign up successful");
        } catch (Exception e) {
            throw new SignUpException(SignUpErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    public void resetPassword(PasswordResetDTO dto) { // dto 를 파라미터로 받아야 하나?
        // 디비에 저장된 사용자인지 확인한다
       boolean userExists = userRepo.existsByLoginId(dto.getUserLoginId());
       if (userExists) { // 사용자가 존재하면

           // 1. 비밀번호 암호화
           String encryptedPassword = encrypt.encryptPassword(dto.getNewPassword());

            //로그
           log.debug("Encrypted password: {}", encryptedPassword);

        // 3.암호화된 비밀번호로 DB 업데이트
           userRepo.updatePassword(encryptedPassword, dto.getUserLoginId());
       }
    }
}