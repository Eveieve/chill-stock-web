package com.chilluminati.chillstock.security;

import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.common.UserType;
import com.chilluminati.chillstock.admin.user.dto.UserDto;
import com.chilluminati.chillstock.admin.user.repository.UserRepository;
import com.chilluminati.chillstock.admin.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class EmailUserDetailsServiceImp implements AuthUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserVo userVo = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다."));

        return EmailUserDetails.builder()
                .userId(userVo.getUserId())
                .userLoginId(userVo.getUserEmail()) // 이메일을 로그인 ID로 사용
                .userPassword(userVo.getUserPassword())
                .userType(userVo.getUserType().toString())     // "ROLE_member" or "ROLE_admin"
                .build();
    }
}
