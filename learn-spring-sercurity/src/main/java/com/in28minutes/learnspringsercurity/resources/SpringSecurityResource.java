package com.in28minutes.learnspringsercurity.resources;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityResource {
    @GetMapping("/csrf-token")
    public CsrfToken retrieveCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
//        {
//            "parameterName": "_csrf",
//                "headerName": "X-CSRF-TOKEN",
//                "token": "3F_81z-CFPhvkpO4nwNf74kqwZaxgFo8vpIrrh9EuzMiVISAumbJswm7JsxC9KeJ-S5r2LET7K6DtW0Rj_BKzXp3j1JHYrSz"
//        }

        //Header에 "X-CSRF-TOKEN" : "3F_81z-CFPhvkpO4nwNf74kqwZaxgFo8vpIrrh9EuzMiVISAumbJswm7JsxC9KeJ-S5r2LET7K6DtW0Rj_BKzXp3j1JHYrSz" 하면
        //POST도 요청할수있다.
    }
}
