package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.GamingConsole;
import com.in28minutes.learnspringframework.game.PacManGame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.in28minutes.learnspringframework.game") //"com.in28minutes.learnspringframework.game" 에서 컴포넌트를 찾아라
public class App03GamingSpringBeans {

//    @Bean
//    public GamingConsole game(){
//        var game = new PacManGame();
//        return game;
//    }

//    @Bean
//    public GameRunner gameRunner(GamingConsole game){ //@ComponentScan 어노테이션 덕분에 GamingConsole Bean 을 찾을수있따.
//        var gameRunner = new GameRunner(game);
//        return gameRunner;
//    }
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(App03GamingSpringBeans.class)){
            context.getBean(GamingConsole.class).up();
            context.getBean(GameRunner.class).run();
        }

        //var game = new MarioGame();
        //var game = new SuperContraGame();
//        var game = new PacManGame();
//        var gameRunner = new GameRunner(game);
//
//        gameRunner.run();
    }
}
