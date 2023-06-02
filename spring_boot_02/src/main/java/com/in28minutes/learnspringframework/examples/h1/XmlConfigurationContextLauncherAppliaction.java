package com.in28minutes.learnspringframework.examples.h1;

import com.in28minutes.learnspringframework.game.GameRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;


public class XmlConfigurationContextLauncherAppliaction {

    public static void main(String[] args) {

        try(var context = new ClassPathXmlApplicationContext("contextConfiguration.xml")){
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println); //Spring에 필수적인 Bean들을 확인할수있다.
            context.getBean(GameRunner.class).run();
        }

     }
}
