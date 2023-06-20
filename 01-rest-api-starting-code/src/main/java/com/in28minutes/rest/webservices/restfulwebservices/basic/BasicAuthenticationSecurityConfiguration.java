package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationSecurityConfiguration {
    //Filter chain

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //basic authentication
        //disabling csrf (세션이 있다면 csrf를 활성화시켜야함) 그러나 REST api엔 세션이 필요하지 않다.
        http.authorizeHttpRequests(auth -> auth.antMatchers(HttpMethod.OPTIONS, "/").permitAll() //모든 url의 option method를 허락한다.
                .anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults()); //기본 인증 - username/password
        //상태가 없는 세션을 설정한다.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf().disable();

        //1. 프리플라이트 요청에 대한 응답이 엑세스 제어 체크를 통과하지 못하는 오류 해결 (모든 사람에게 option 요청 (request method : option) 을 허용해야한다.)
        //2. basic auth (기본 인증 체크 api 구현)
        return http.build();
    }
}
