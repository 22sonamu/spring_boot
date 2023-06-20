package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults()); //기본 인증 - username/password
        //상태가 없는 세션을 설정한다.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf().disable();
        return http.build();
    }
}
