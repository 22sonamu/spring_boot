package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

record Person(String name, int age){};
//record - JDK 16에 추가된 기능 , Getter , Setter 안만들어도 됨
@Configuration
public class HelloWorldConfiguration { //스프링 설정 파일, Bean 생성 가능
    @Bean
    public String name(){
        return "Mara";
    }

    @Bean
    public int age(){
        return 25;
    }

    @Bean
    public Person person(){
        return new Person("Ravi", 20);
    }
}
