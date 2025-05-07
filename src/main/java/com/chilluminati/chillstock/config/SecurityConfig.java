package com.chilluminati.chillstock.config;

import com.chilluminati.chillstock.security.CustomLoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.Filter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/nonuser/**").permitAll()
                .antMatchers("/member/**").hasRole("MEMBER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/nonuser/login")
                .loginProcessingUrl("/nonuser/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(new CustomLoginSuccessHandler()) // 커스텀핸들러 등록
                .failureUrl("/") //실패하면 메인
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/nonuser/access-denied"); // 접근 거부 페이지

        log.info("SecurityConfig filter chain 설정 완료");
    }
}