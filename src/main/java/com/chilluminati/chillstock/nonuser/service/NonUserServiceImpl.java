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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NonUserServiceImpl implements NonUserService {

    private final UserRepo userRepo;
    private final BizRepo bizRepo;
    private final ModelMapper modelMapper;

    @Override
    public boolean checkEmailDuplicate(EmailDupDTO dto) {
        return userRepo.existsByEmail(dto.getUserEmail());
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
            UserVO userVo = modelMapper.map(signupDto, UserVO.class);
            userRepo.insertUser(userVo);  // 이때 userId가 생성됨

            BizVO bizVO = modelMapper.map(signupDto, BizVO.class);
            bizVO.setUserId(userVo.getUserId());  // 명시적으로 userId 세팅
            bizRepo.insertBiz(bizVO);  // 이제는 userId가 null이 아님
            System.out.println(bizVO.getUserId());
        } catch (Exception e) {
            throw new SignUpException(SignUpErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    public void resetPassword(PasswordResetDTO passwordResetDto) { // dto 를 파라미터로 받아야 하나?
        // 디비에 저장된 사용자인지 확인한다
       boolean userExists = userRepo.existsByLoginId(passwordResetDto.getUserLoginId());
       if (userExists) { // 사용자가 존재하면
           // 패스워드 재서러정한다
           userRepo.updatePassword(passwordResetDto.getUserLoginId(), passwordResetDto.getNewPassword());
       }
    }
}