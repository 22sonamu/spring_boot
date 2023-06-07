# Spring Boot의 목표

-----------

1. **프로덕션 환경**에서 사용가능한 어플리케이션을 **빠르게** 빌드할 수 있도록 돕는 것
    
    - 빠르게
      
        - Spring Initializr (start.spring.io)
        - Spring Boot Starter Projects : 프로젝트의 의존성을 빠르게 정의할수있다.
        - Spring Boot Auto Configuration : 클래스 경로에 있는 의존성에 따라 자동으로 설정이 주입된다.
        - Spring Boot DevTools : 수동으로 서버를 다시 시작하지 않아도 어플리케이션을 변경할 수 있다.
   
    - 프로덕션 환경에 사용 가능하도록

        - Logging
        - Different Configuration for Different Environments : ex. dev , prod 환경 
          
            - Profies, ConfigurationProperties
        - Monitoring 

# Spring Boot Starter Projects

--------


원래 어플리케이션을 빌드하기 위해서는 여러가지 프레임워크가 필요하다 (ex. Spring, Spring MVC, Tomcat, Json conversion....)
이런 프레임워크들을 그룹화해서 어플리케이션을 쉽게 빌드할 수 있게 해주는 것이 Spring Boot Starter Project이다.
xml에 필요한 의존성들을 추가해서 그룹화할수 있다.


ex) spring boot starter web
~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
~~~

웹 개발에 필요한 

- spring-boot-starter
- jackson
- spring-core
- spring-mvc
- spring-boot-starter-tomcat

을 포함하고있는 의존성이다.

