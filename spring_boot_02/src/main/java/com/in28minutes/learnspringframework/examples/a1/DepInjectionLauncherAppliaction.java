package com.in28minutes.learnspringframework.examples.a1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class YourBusinessClass{
    //생성자나 setter 없이 자동 필드 주입

    Dependency1 dependency1;


    Dependency2 dependency2;

    public Dependency1 getDependency1() {
        return dependency1;
    }

    @Autowired //Setter 에 Autowired 작성해도
    //@Autowired
    //Dependency1 dependency1 이랑 같은 효과 ( 의존성 주입 )
    public void setDependency1(Dependency1 dependency1) {
        System.out.println("Setter Injection - setDependency1");
        this.dependency1 = dependency1;
    }


    public Dependency2 getDependency2() {
        return dependency2;
    }

    @Autowired
    public void setDependency2(Dependency2 dependency2) {
        System.out.println("Setter Injection - setDependency2");
        this.dependency2 = dependency2;
    }

    public String toString(){
        return "Using "+ dependency1 +" and " + dependency2;
    }
}

@Component
class Dependency1{

}
@Component
class Dependency2{

}

@Configuration
@ComponentScan
public class DepInjectionLauncherAppliaction {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(DepInjectionLauncherAppliaction.class)){
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println); //Spring에 필수적인 Bean들을 확인할수있다.
            System.out.println(context.getBean(YourBusinessClass.class));
        }

    }
}
