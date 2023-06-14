package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource=messageSource;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldVariable(@PathVariable String name){
        return new HelloWorldBean("Hello World" + name);
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(){
        //현재 쓰레드와 관련된 locale 반환
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
        //1. 나라별 인사를 저장해야함
        //2. 값을 가져올 수 있는 코드를 작성
    }
}
