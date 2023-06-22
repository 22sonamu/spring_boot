package com.in28minutes.learnspringsercurity.jwt;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class JwtSecurityConfiguration {

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {auth.anyRequest().authenticated();});
        //http.formLogin(withDefaults()); //로그인 form 없애기

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //세션을 사용하지 않는다.
        http.httpBasic(withDefaults());
        http.csrf().disable(); //csrf 설정 disable
        http.headers().frameOptions().sameOrigin(); //h2는 frame tag을 사용하는데 , Spring Security는 자동으로 막는다. //same Origin에서 요청이 올때, frame을 허용하는 옵션이다.
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt); //정적 메서드 참조
        return http.build();
    }


    @Bean
    public DataSource dataSource(){

        return new EmbeddedDatabaseBuilder()//새로운 임베디드 데이터베이스를 만든다.
                .setType(EmbeddedDatabaseType.H2) //데이터베이스 타입은 H2
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build(); //자체 데이터 소스


    }

        @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        var user = User.withUsername("in28minutes")
                //.password("{noop}dummy")
                .password("dummy")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("USER")
                .build();

        var admin = User.withUsername("admin")
                //.password("{noop}dummy")
                .password("dummy")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("ADMIN", "USER")
                .build();

        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin); //admin , admin권한 , admin, user권한 이렇게 두가지의 데이터가 authorities 테이블에 저장
        return jdbcUserDetailsManager;

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();    }


}
