package com.in28minutes.learnspringframework.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.in28minutes.learnspringframework.game") //"com.in28minutes.learnspringframework.game" 에서 컴포넌트를 찾아라
public class GamingAppLauncherAppliaction {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(GamingAppLauncherAppliaction.class)){
            context.getBean(GamingConsole.class).up(); //Primary로 설정된 MarioGame이 출력된다.
            context.getBean(GameRunner.class).run(); //생성자에 Qualifier로 지정해준 SuperContraGame이 실행된다.
        }

    }
}
