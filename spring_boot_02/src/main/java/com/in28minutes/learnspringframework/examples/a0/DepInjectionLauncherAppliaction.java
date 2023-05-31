package com.in28minutes.learnspringframework.examples.a0;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
@ComponentScan
public class DepInjectionLauncherAppliaction {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(DepInjectionLauncherAppliaction.class)){
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println); //Spring에 필수적인 Bean들을 확인할수있다.
        }

    }
}
