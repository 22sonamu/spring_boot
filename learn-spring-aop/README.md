# AOP (Aspect Oriented Programming)

-----


~~~
공통 관심사를 구현할때 사용함 (ex. Logging, Security)
~~~


1. 곹옹 관심사는 하나의 aspect로 구현한다.
2. aspect를 어디에 적용할 것인지 명시하는 코드를 작성한다.


- 유명한 Framework들

    - Spring AOP

        - Intercept method calls to Spring Beans
    - AspectJ

        - Intercept method call on any Java class



# AOP 용어

-------

### Compile Time


~~~Java

    @Before("execution(* com.in28minutes.learnspringaop.aopexcample.business.*.*(..))")
    public void logMethodCall(JoinPoint joinpoint){
        logger.info("Before aspect - method is called - {}", joinpoint);
    }
~~~

- Advice : 실행할 코드


~~~Java
logger.info("Before aspect - method is called - {}", joinpoint);
~~~

- Pointcut : 인터셉트 할 메소드


~~~java
 @Before(execution(* com.in28minutes.learnspringaop.aopexcample.business.*.*(..)))
~~~


- Aspect : Advice 와 Pointcut의 조합
~~~Java

    @Before("execution(* com.in28minutes.learnspringaop.aopexcample.business.*.*(..))")
    public void logMethodCall(JoinPoint joinpoint){
        logger.info("Before aspect - method is called - {}", joinpoint);
    }
~~~


- Weaver : AOP를 구현한 프레임워크, Advice가 적시에 구현되는지 확인하는 주체 (ex. Spring AOP)



### Runtime


- Join Point : Advice 실행 인스턴스
