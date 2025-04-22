package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.nonuser.dto.LoginIdDupDTO;
import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.exception.SignUpErrorCode;
import com.chilluminati.chillstock.nonuser.exception.SignUpException;
import com.chilluminati.chillstock.nonuser.exception.UserNotFoundException;
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
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final BizRepo bizRepo;
    private final ModelMapper modelMapper;


    @Override
    public boolean checkLoginIdDuplicate(LoginIdDupDTO loginIdDupDTO) {
        return userRepo.existsByLoginId(loginIdDupDTO.getUserLoginId());
    }

    /**
     * 회원가입
     * @param signupDto 회원가입 요청 데이터
     */
    @Override
    @Transactional
    public void signUp(SignUpDTO signupDto) {

        modelMapper.typeMap(SignUpDTO.class, BizVO.class)
                .addMappings(mapper -> mapper.skip(BizVO::setUserId));

        // 1. 비밀번호 확인 일치 여부
        if (!signupDto.getUserPassword().equals(signupDto.getUserPasswordCheck())) {
            throw new SignUpException(SignUpErrorCode.PASSWORD_MISMATCH);
        }

        // 2. 로그인 ID 중복 체크
        if (userRepo.findByLoginId(signupDto.getUserLoginId()) != null) {
            throw new SignUpException(SignUpErrorCode.DUPLICATE_LOGIN_ID);
        }

        // 3. 이메일 중복 체크
        if (userRepo.findByEmail(signupDto.getUserEmail()) != null) {
            throw new SignUpException(SignUpErrorCode.DUPLICATE_EMAIL);
        }

        // 4. 사업자 등록번호 중복 체크
        if (bizRepo.findByBusinessRegistNum(signupDto.getBusinessRegistNum()) != null) {
            throw new SignUpException(SignUpErrorCode.DUPLICATE_BUSINESS_REGIST_NUM);
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
    public UserVO findByLoginId(String loginId) {
        UserVO user = userRepo.findByLoginId(loginId);
        if (user == null) {
            throw new SignUpException(SignUpErrorCode.USER_NOT_FOUND_BY_LOGIN_ID);
        }
        return user;
    }

    @Override
    public UserVO findByEmail(String email) {
        UserVO user = userRepo.findByEmail(email);
        if (user == null) {
            throw new SignUpException(SignUpErrorCode.USER_NOT_FOUND_BY_EMAIL);
        }
        return user;
    }

    @Override
    public UserVO findByUserId(Integer userId) {
        UserVO user = userRepo.findByUserId(userId);
        if (user == null) {
            throw new SignUpException(SignUpErrorCode.USER_NOT_FOUND_BY_USER_ID);
        }
        return user;
    }

    /**
     * 사용자 로그인 아이디를  입력 받고, 이메일이 디비에 있으면 새로운 비밀번호를 입력받아 비밀번호 재설정하기
     * @param
     */
    @Override
    public void resetPassword(PasswordResetDTO passwordResetDto) { // dto 를 파라미터로 받아야 하나?

        UserVO user = userRepo.findByLoginId(passwordResetDto.getUserLoginId());
        if(user != null) {
            userRepo.updatePassword(passwordResetDto.getNewPassword(), passwordResetDto.getUserLoginId());
        }
    }

    /**
     * 이메일을 입력받아 로그인 아이디를 반환한다
     * @param email
     * @return
     */
    @Override
    public String findLoginId(String email) {
        String loginId = userRepo.findLoginId(email);
        if (loginId == null) {
            throw new UserNotFoundException("해당 이메일로 등록된 계정을 찾을 수 없습니다.");
        }
        return loginId;
    }



}