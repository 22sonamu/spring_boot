package com.in28minutes.learnspringframework.examples.d1;

import com.in28minutes.learnspringframework.examples.c1.BusinessCalculationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class ClassA{

}

@Component
@Lazy //context 만들때 초기화되는것을 막는다.
    //Class Bean 을 사용하여야 초기화된다.
class ClassB{

    private ClassA classA;
    public ClassB(ClassA classA){
        System.out.println("Some Initialization logic");
        this.classA = classA;
    }
    public void doSomething(){
        System.out.println("Do Something");
    }
}

@Configuration
@ComponentScan
public class LazyInitializationLauncherAppliaction {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(LazyInitializationLauncherAppliaction.class)){
            System.out.println("Initialization of context is completed");

            context.getBean(ClassB.class).doSomething();
            //Initialization of context is completed 호출
            //Do Something 호출
        }

    }
}
