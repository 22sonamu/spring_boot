package com.in28minutes.learnspringframework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
@Qualifier("SuperContraGameQualifier")
public class SuperContraGame implements GamingConsole{

    // 동일한 버튼 -> MarioGame이랑 다른 기능을 수행한다.
    public void up(){
        System.out.println("Jump");
    }

    public void down(){
        System.out.println("Sit down");
    }

    public void left(){
        System.out.println("Go back");
    }

    public void right(){
        System.out.println("Shoot a bullet");
    }
}
