package com.chilluminati.chillstock.security;

import com.chilluminati.chillstock.admin.user.repository.UserRepository;
import com.chilluminati.chillstock.admin.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailUserDetailsServiceImp implements AuthUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserVO userVo = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the provided email address."));

        return EmailUserDetails.builder()
                .userLoginId(userVo.getUserEmail()) // Use email as login ID
                .userPassword(userVo.getUserPassword())
                .userType(userVo.getUserType().toString())     // "MEMBER" or "ADMIN"
                .userId(userVo.getUserId())
                .build();
    }
}
