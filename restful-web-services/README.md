# api 의 background 동작


----------

### 1. 요청은 어떻게 처리될까?
    
- DispatcherServlet : root url에 매핑된다
- DispatcherServlet이 알맞은 컨트롤러에 매핑한다.
- DispatcherServlet은 자동 설정에 의해 설정된다.

### 2. 어떻게 HelloWorldBean 객체가 JSON으로 변환될까?


- @ResponseBody
    
- JacksonHttpMessageConverters
    
    - JacksonHttpMessageConvertersConfiguration (Auto Configuration)

### 3. 올바르지 않은 주소가 들어왔을 때 ?

- AutoConfiguration 
  
    - ErrorMvcAutoConfiguration


### 어떻게 모든 jar파일을 사용할수있는지?

- Spring Boot Starter Web
  
    - spring-webmvc, spring-web, spring-boot-tomcat, spring-boot-starter-json