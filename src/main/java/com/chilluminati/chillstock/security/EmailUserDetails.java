package com.chilluminati.chillstock.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailUserDetails implements UserDetails {

    private Integer userId; // DB PK
    private String userLoginId; // 이메일
    private String userPassword;
    private String userType; // "MEMBER" or "ADMIN"

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_" + userType); // ex: "ROLE_ADMIN"
    }

    @Override
    public String getUsername() {
        return userLoginId; //이메일
    }

    @Override
    public String getPassword() {
        return userPassword; //비밀번호
    }

    // 계정 상태 true 고정
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
