package com.in28minutes.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailManager(){

        UserDetails userDetails1 = createNewUser("in28minutes", "dummy");
        UserDetails userDetails2 = createNewUser("ranga", "dummydummy");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
        UserDetails userDetails = User
                .builder()
                .passwordEncoder(passwordEncoder)
                .username(username) //로그인 아이디, 비번 설정
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //SecurityFilterChain - 아무것도 설정하지 않으면
    //1. 모든 URL이 보호된다.
    //2. 승인되지 않은 요청에선 로그인 url이 나온다.

    // 웹 요청이 들어오면 이 체인이 먼저 처리한다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //모든 요청이 승인되도록한다.
        http.authorizeHttpRequests(
          auth -> auth.anyRequest().authenticated());
        //승인되지 않은 요청이 있다면, formLogin을 띄워준다.
        http.formLogin(withDefaults());
        //csrf 비활성화
        http.csrf().disable();
        //어플리케이션에서 프레임 사용 비활성화
        http.headers().frameOptions().disable();

        return http.build();
    }
}
