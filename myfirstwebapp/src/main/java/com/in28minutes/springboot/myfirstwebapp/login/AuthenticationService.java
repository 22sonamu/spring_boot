package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {
    public boolean authenticate(String username, String password){

        boolean isValidUsername = username.equalsIgnoreCase("in28minutes");
        boolean isValidPassword = password.equalsIgnoreCase("dummy");


       return isValidPassword && isValidUsername;
    }
}
