package com.in28minutes.learnspringframework.examples.d1;

import com.in28minutes.learnspringframework.examples.c1.BusinessCalculationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class ClassA{

}

@Component
class ClassB{

    private ClassA classA;
    public ClassB(ClassA classA){
        System.out.println("Some Initialization logic");
        this.classA = classA;
    }
}

@Configuration
@ComponentScan
public class LazyInitializationLauncherAppliaction {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(LazyInitializationLauncherAppliaction.class)){
            //Some Initializatin logic 출력. 함수를 호출하거나 getBean하지 않아도 자동으로 Bean이 초기화된다.
        }

    }
}
