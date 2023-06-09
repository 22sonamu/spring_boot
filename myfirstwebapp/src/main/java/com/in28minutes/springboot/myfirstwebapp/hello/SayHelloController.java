package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody //문자열 View를 Return해준다.
    public String sayHello(){
        return "Hello!";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml(){
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> My First HTML Page</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("My First html page with body");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    //sayHello.jsp -> Spring이 정한 폴더 안에 정의해야한다.
    // source/main/resource/META-INF/resources/WEB-INF/jsp/####.jsp
    @RequestMapping("say-hello-jsp")
    //@ResponseBody -> 가 있으면 sayHello 자체를 return할것이다.
    public String sayHelloJsp(){
        return "sayHello";
    }

}
