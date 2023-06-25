package com.in28minutes.learnspringsercurity.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

//@RestController
public class JwtAuthenticationResource {
    private JwtEncoder jwtEncoder;
    private JwtAuthenticationResource(JwtEncoder jwtEncoder){
        this.jwtEncoder = jwtEncoder;
    }



    @PostMapping("/authenticate")
    public JwtResponse authenticate(Authentication authentication) {
        return new JwtResponse(createToken(authentication));
//        return authentication 이면 :
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
    }

    private String createToken(Authentication authentication) {
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 15)) //jwt 만료 시간
                .subject(authentication.getName())
                .claim("scope", createScope(authentication))
                .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims); //claims로부터 파라미터를 만들고, 인코딩한후 getToken해서 Return 한다.
        return jwtEncoder.encode(parameters).getTokenValue();
    }

    private Object createScope(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(" ")); //모든 권한을 공백으로 구분해서 취합한다.
    }
}

record JwtResponse(String token){}
