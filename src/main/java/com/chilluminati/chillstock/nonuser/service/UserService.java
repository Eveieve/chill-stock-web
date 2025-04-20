package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.vo.UserVO;

public interface UserService {
    void signUp(SignUpDTO signupDto);
    UserVO findByLoginId(String loginId);
    UserVO findByEmail(String email);
    UserVO findByUserId(Integer userId);

}
