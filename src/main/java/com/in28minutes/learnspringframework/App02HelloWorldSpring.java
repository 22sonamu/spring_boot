package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.PacManGame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
        //1 : Launch a Spring Context
        var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        //2 : Configure the things that we want Spring to manage - @Configuration
        //HelloWorldConfiguration - @Configuration
        //name - @Bean

        //3: Spring이 관리하는 Bean 검색
        System.out.println(context.getBean("name"));//메서드 이름




    }
}
