package com.in28minutes.learnspringframework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
        //1 : Launch a Spring Context
        try(var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)){
            //2 : Configure the things that we want Spring to manage - @Configuration
            //HelloWorldConfiguration - @Configuration
            //name - @Bean

            //3: Spring이 관리하는 Bean 검색
            System.out.println(context.getBean("name"));//메서드 이름

            System.out.println(context.getBean("age"));

            System.out.println(context.getBean("person"));

            System.out.println(context.getBean("address2"));

            //이렇게도 접근 가능 , 단 Address로 return하는 Bean이 한개만있어야한다.
            System.out.println(context.getBean(Address.class));

            System.out.println(context.getBean("person2MethodCall"));

            System.out.println(context.getBean("person3Parameters"));

            //Spring이 관리중인 Bean 나열
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);

            System.out.println(context.getBean(Person.class));

            System.out.println(context.getBean("person5Qualifier"));
        }


    }
}
