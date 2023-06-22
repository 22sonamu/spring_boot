package com.in28minutes.learnspringsercurity.basic;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class BasicAuthSecurityConfiguration {

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {auth.anyRequest().authenticated();});
        //http.formLogin(withDefaults()); //로그인 form 없애기

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //세션을 사용하지 않는다.
        http.httpBasic(withDefaults());
        http.csrf().disable(); //csrf 설정 disable
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){ //사용자 정보 가져오는 Interface
        var user = User.withUsername("in28minutes")
                .password("{noop}dummy")//{noop} -> 인코딩 x
                .roles("USER")
                .build();

        var admin = User.withUsername("admin")
                .password("{noop}dummy")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);

    }
}
