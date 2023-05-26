package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.PacManGame;

public class App01GamingBasicJava {
    public static void main(String[] args) {

        //var game = new MarioGame();
        //var game = new SuperContraGame();
        var game = new PacManGame(); // 1. Object Creation
        var gameRunner = new GameRunner(game);
            // 2. Object Creation + Wiring of Dependencies ( 의존성 주입 )
            // Game is a Dependency of GameRunner (의존성)

        gameRunner.run();
    }
}
