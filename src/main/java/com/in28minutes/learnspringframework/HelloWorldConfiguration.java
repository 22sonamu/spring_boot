package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration { //스프링 설정 파일, Bean 생성 가능
    @Bean
    public String name(){
        return "Mara";
    }
}
