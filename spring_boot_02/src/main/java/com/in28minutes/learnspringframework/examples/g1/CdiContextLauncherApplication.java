package com.in28minutes.learnspringframework.examples.g1;

import com.in28minutes.learnspringframework.examples.c1.BusinessCalculationService;
import com.in28minutes.learnspringframework.examples.c1.DataService;
import jakarta.inject.Named;
import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component
@Named //jakarta (= component)
class BusinessService{
    private DataService dataService;


    public DataService getDataService(){

        return dataService;
    }

    @Autowired
    public void setDataService(DataService dataService) {
        System.out.println("Setter Injection");
        this.dataService = dataService;
    }


}

@Configuration
@ComponentScan("com.in28minutes.learnspringframework.examples.c1 , com.in28minutes.learnspringframework.examples.g1")
public class CdiContextLauncherApplication {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(CdiContextLauncherApplication.class)){
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println); //Spring에 필수적인 Bean들을 확인할수있다.
            System.out.println(context.getBean(BusinessService.class).getDataService());
        }

    }
}
