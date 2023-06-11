package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") //세션에 등록, 사용하고싶은 변수가 있는 컨드롤러에 모두 넣어줘야한다.
public class LoginController {

    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //Model
    @RequestMapping(value = "login" , method = RequestMethod.GET)
    public String gotoLoginPage(){
        return "login";
    }

    @RequestMapping(value = "login" , method = RequestMethod.POST)
    public String gotoWelcomePage(@RequestParam String name, String password, ModelMap model){
        if(authenticationService.authenticate(name,password)){
            //Authentication
            //name - in28minutes
            //password - dummy

            model.put("name", name);

            return "welcome";
        }
        model.put("errorMessage", "Invalid Credentials! Please try again");
        return "login";
    }
}
