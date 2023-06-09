package com.in28minutes.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    //sout보다 logger가 권장된다.
    private Logger logger = LoggerFactory.getLogger(getClass());
    //Model
    @RequestMapping("login")
    public String gotoLoginPage(@RequestParam String name , ModelMap model){
        //jsp 파일로 전달해주기
        model.put("name", name);
        logger.debug("RequestParam is {}" , name);
        return "login";
    }
}
