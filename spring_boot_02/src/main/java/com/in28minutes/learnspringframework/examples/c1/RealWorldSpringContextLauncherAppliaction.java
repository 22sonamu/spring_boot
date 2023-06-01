package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class RealWorldSpringContextLauncherAppliaction {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(RealWorldSpringContextLauncherAppliaction.class)){
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println); //Spring에 필수적인 Bean들을 확인할수있다.

            System.out.println(context.getBean(BusinessCalculationService.class).findMax());
        }

    }
}
