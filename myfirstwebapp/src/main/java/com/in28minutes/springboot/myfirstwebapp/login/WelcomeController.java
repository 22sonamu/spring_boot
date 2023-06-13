package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") //세션에 등록, 사용하고싶은 변수가 있는 컨드롤러에 모두 넣어줘야한다.
public class WelcomeController {


    //Model
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String gotoWelcomePage(ModelMap model){
        model.put("name", getLoggedinUsername());
        return "welcome";
    }
    private String getLoggedinUsername(){
        //현재 인증된 사용자 정보를 전달
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
