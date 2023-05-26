package com.in28minutes.learnspringframework.game;

public class GameRunner {
    // MarioGame game;
    // 강한 결합 , MarioGame이 아니면 Game을 Run 할수 없다.
    // SuperContraGame 을 실행하려면 코드를 변경해야한다.

    private SuperContraGame game;
    public GameRunner(SuperContraGame game){
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
