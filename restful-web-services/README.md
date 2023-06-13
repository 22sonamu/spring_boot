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


# Request Method

--------

1. GET : 리소스에서 상세 정보를 검색할때
2. POST : 새 리소스를 만들고자 할 때
3. PUT : 기존 사용자의 정보를 업데이트하려할때
4. PATCH : 기존 리소스의 일부만 업데이트할때
5. DELETE : 기존 리소스를 삭제할때

### 개발할 api 정리

- Users
  
    - GET /users : 모든 유저 정보 검색
    - POST /users : 새 유저 생성
    - GET /users/{id} : 사용자 한명의 정보 검색
    - DELETE /users/{id} : 사용자 삭제
    - GET /users/{id}/posts : 특정 사용자가 작성한 게시물
    - POST /users/{id}/posts : create 게시물
    - GET /users/{id}/posts/{post_id} : 게시물 상세 조회
