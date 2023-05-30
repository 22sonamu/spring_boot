package com.in28minutes.learnspringframework.game;

import org.springframework.stereotype.Component;

@Component
public class GameRunner {
    // MarioGame game;
    // 강한 결합 , MarioGame이 아니면 Game을 Run 할수 없다.
    // SuperContraGame 을 실행하려면 코드를 변경해야한다.

    // 느슨한 결합 , GameConsole을 implements 한 게임들은 모두 실행 가능하다.
    private GamingConsole game;
    public GameRunner(GamingConsole game){
        this.game = game;
    }

    public void run(){
        System.out.println( "Running game" + game );
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
