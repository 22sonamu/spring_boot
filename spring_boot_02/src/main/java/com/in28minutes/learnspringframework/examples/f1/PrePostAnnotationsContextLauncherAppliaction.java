package com.in28minutes.learnspringframework.examples.f1;

import com.in28minutes.learnspringframework.examples.c1.BusinessCalculationService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class SomeClass{
    private SomeDependency someDependency;

    public SomeClass(SomeDependency someDependency){
        this.someDependency = someDependency;
        System.out.println("All dependencies are ready");
    }
    @PostConstruct //의존성을 연결하는 대로 초기화를 실행하기
    public void initialize(){
        someDependency.getReady();
    }

    @PreDestroy // 컨테이너에서 인스턴스를 삭제할때 실행
    public void cleanup(){
        System.out.println("Clean up");
    }
}

@Component
class SomeDependency{

    public void getReady() {
        System.out.println("Some logic using SomeDependency");
    }
}

@Configuration
@ComponentScan
public class PrePostAnnotationsContextLauncherAppliaction {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(PrePostAnnotationsContextLauncherAppliaction.class)){
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println); //Spring에 필수적인 Bean들을 확인할수있다.

        }

    }
}
