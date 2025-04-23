package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.nonuser.dto.EmailDupDTO;
import com.chilluminati.chillstock.nonuser.dto.LoginIdDupDTO;
import com.chilluminati.chillstock.nonuser.dto.PasswordResetDTO;
import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;

public interface NonUserService {  // NonUser 로 바꾸기 ? 여유 있다면
    /**
     * 로그인 아이디로 사용자가 이미 존재하는지 확인
     * @param loginId
     * @return
     */
  boolean existsByLoginId(String loginId);

  /**
   * 로그인 아이디가 중복인지 체크한다
   * @param loginIdDupDTO
   * @return 중복이면 true, 중복이 아니면 false 반환
   */
  boolean checkLoginIdDuplicate(LoginIdDupDTO loginIdDupDTO);

  /**
   * 이메일이 중복인지 체크한다
   * @param emailIdDupDTO
   * @return 중복이면 true, 중복이 아니면 false 반환
   */
    boolean checkEmailDuplicate(EmailDupDTO emailIdDupDTO);

  /**
   * 회원가입 한다
   * @param signupDto 회원 가입시 받은 입력값
   */
  void signUp(SignUpDTO signupDto);


  /**
   * 비밀번호를 재설정한다
   * @param passwordResetDto
   */
  void resetPassword(PasswordResetDTO passwordResetDto);

  /**
   * 로그인 아이디를 찾는다
   * @param email 이메일
   * @return 로그인 아이디
   */
    String findLoginIdByEmail( String email);
}
