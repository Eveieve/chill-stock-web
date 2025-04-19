package com.chilluminati.chillstock.nonuser.service;

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
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final BizRepo bizRepo;
    private final ModelMapper modelMapper;



    /**
     * 회원가입
     * @param signupDto 회원가입 요청 데이터
     */
    @Override
    @Transactional
    public void signUp(SignUpDTO signupDto) {

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

        try {

            UserVO userVo = modelMapper.map(signupDto, UserVO.class);
            userRepo.insertUser(userVo); // DB에서 userId 생성됨

            // 생성된 userId를 DTO나 BizVO에 세팅해줘야 한다
            BizVO bizVO = modelMapper.map(signupDto, BizVO.class);
            bizVO.setUserId(userVo.getUserId());
            bizRepo.insertBiz(bizVO);

        } catch (Exception e) {
            throw new SignUpException(SignUpErrorCode.DATABASE_ERROR);
        }


    }

    @Override
    public void findByLoginId(String loginId) {

    }

    @Override
    public void findByEmail(String email) {

    }

    @Override
    public void findByUserId(String userId) {

    }
}