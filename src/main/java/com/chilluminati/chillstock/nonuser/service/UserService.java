package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;

public interface UserService {
    void signUp(SignUpDTO signupDto);
    void findByLoginId(String loginId);
    void findByEmail(String email);
    void findByUserId(String userId);

}
