package com.in28minutes.learnspringframework.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person(String name, int age, Address address) {
};
//record - JDK 16에 추가된 기능 , Getter , Setter 안만들어도 됨

record Address(String firstLine, String city) {
};

@Configuration
public class HelloWorldConfiguration { //스프링 설정 파일, Bean 생성 가능
    @Bean
    public String name() {
        return "Mara";
    }

    @Bean
    public int age() {
        return 25;
    }

    @Bean
    @Primary
    public Person person() {
        return new Person(
                "Ravi",
                20,
                new Address("Main Street", "Seoul")
        );
    }

    @Bean
    public Person person2MethodCall() {
        return new Person(name(), age(), address()); //Mara, 25
    }

    @Bean
    public Person person3Parameters(String name, int age, Address address2) { //매개변수로 설정 가능
        // person2MethodCall 과 같은 결과
        return new Person(name, age, address2);
    }

    //Bean 이름 설정 , address로 접근 불가능
    @Bean(name = "address2")
    @Primary
    public Address address() {
        return new Address("을지로", "서울");
    }

    @Bean(name = "address3")
    @Qualifier("address3qualifier")
    public Address address3() {
        return new Address("을지로3", "서울3");
    }

    @Bean
    public Person person4Parameters(String name, int age, Address address) {
        return new Person(name, age, address);
    }

    @Bean
    public Person person5Qualifier(String name, int age, @Qualifier("address3qualifier") Address address) {
        return new Person(name, age, address);
    }

}
