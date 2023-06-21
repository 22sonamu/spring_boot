package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        //1. 모든 요청은 인증을 받아야한다.
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        //2. 만약 요청이 인증되지 않았다면, web page를 보여준다.
        http.httpBasic(Customizer.withDefaults());
        //여기까지가 원래 Spring Security의 filterchain의 기능이다.

        //csrf 해제
        http.csrf().disable();

        return http.build();

    }
}
