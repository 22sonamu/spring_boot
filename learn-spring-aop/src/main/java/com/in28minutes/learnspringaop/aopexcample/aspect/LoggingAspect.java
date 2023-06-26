package com.in28minutes.learnspringaop.aopexcample.aspect;

;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //해당 경로에 있는 패키지에서 함수가 실행되기 전에 logMethodCall을 실행시키겠다.
    @Before("execution(* com.in28minutes.learnspringaop.aopexcample.business.*.*(..))")
    public void logMethodCall(JoinPoint joinpoint){
        logger.info("Before aspect - method is called - {}", joinpoint);
    }
}
