package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.vo.UserVO;

public interface UserService {
    void signUp(SignUpDTO signupDto);
    UserVO findByLoginId(String loginId);
    UserVO findByEmail(String email);
    UserVO findByUserId(Integer userId);

    // email 받아서 정보 있으면 비밀번호 리셋해주기
    void resetPassword(PasswordResetDTO passwordResetDto);

    // 이메일로 로그인 아이디 찾기
    String findLoginId( String email);
}
