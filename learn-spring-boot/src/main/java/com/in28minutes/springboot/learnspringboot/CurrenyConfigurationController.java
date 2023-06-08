package com.in28minutes.springboot.learnspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//http://localhost:8080/courses

@RestController
public class CurrenyConfigurationController {

    @Autowired
    private  CurrencyServiceConfiguration currencyServiceConfiguration;
    @RequestMapping("/currency-configuration")
    public CurrencyServiceConfiguration retrieveAllCourses(){
        return currencyServiceConfiguration;
    }

}
