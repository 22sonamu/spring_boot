package com.in28minutes.learnspringsercurity.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationResource {

    @PostMapping("/authenticate")
    public Authentication authenticate(Authentication authentication){
        return authentication;
//        {
//            "authorities": [
//            {
//                "authority": "ROLE_USER"
//            }
//    ],
//            "details": {
//            "remoteAddress": "0:0:0:0:0:0:0:1",
//                    "sessionId": null
//        },
//            "authenticated": true,
//                "principal": {
//            "password": null,
//                    "username": "in28minutes",
//                    "authorities": [
//            {
//                "authority": "ROLE_USER"
//            }
//        ],
//            "accountNonExpired": true,
//                    "accountNonLocked": true,
//                    "credentialsNonExpired": true,
//                    "enabled": true
//        },
//            "credentials": null,
//                "name": "in28minutes"
//        }
//    }
}
