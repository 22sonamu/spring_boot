package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    //Model
    @RequestMapping("login")
    public String gotoLoginPage(@RequestParam String name , ModelMap model){
        //jsp 파일로 전달해주기
        model.put("name", name);
        System.out.println("RequestParam is " + name);
        return "login";
    }
}
