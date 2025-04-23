package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.nonuser.dto.EmailDupDTO;
import com.chilluminati.chillstock.nonuser.dto.LoginIdDupDTO;
import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.vo.UserVO;

public interface UserService {  // NonUser 로 바꾸기 ? 여유 있다면

    // 로그인아이디 중복 체크
    boolean checkLoginIdDuplicate(LoginIdDupDTO loginIdDupDTO);

    // 이메일 중복 체크
    boolean checkEmailDuplicate(EmailDupDTO emailIdDupDTO);

    // 회원가입
    void signUp(SignUpDTO signupDto);
    UserVO findByLoginId(String loginId);
    UserVO findByEmail(String email);
    UserVO findByUserId(Integer userId);

    // email 받아서 정보 있으면 비밀번호 리셋해주기
    void resetPassword(PasswordResetDTO passwordResetDto);

    // 이메일로 로그인 아이디 찾기
    String findLoginId( String email);
}
