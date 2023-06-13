# JSP

--------

/say-hello-jsp => SayHelloController - sayHelloJsp Method => sayHello

# Login Jsp

--------

/login => com.in28minutes.springboot.myfirstwebapp.login.LoginController => login.jsp

# How does Web work?

- A : Browser 가 request를 보낸다.
  
    - HttpRequest
- B : Server가 request를 처리한다.
    
    - Spring boot web application

- C : 서버가 response한다.
    
    - HttpResponse


# 아키텍처의 역사

--------


1. 모델 1 아키텍쳐
   
  - 모든 코드를 view에 작성하였다. (.jsp)
  - 컨트롤러 개념 X
  - 주제별 분류 X

2. 모델 2 아키텍처
  
  - Model : 뷰를 생성하는데 사용하는 데이터
  - View : 사용자에게 보여주는 역할
  - Controller : 전체 흐름 제어
  - 로직이 주제별로 정리되어있다.
  - 장점 : 유지보수에 좋다
  - 단점 : 공통 기능을 구현하기 어렵다.

3. Front Controller 
  
  - 브라우저에서 오는 모든 요청은 Front Controller(중앙 컨트롤러) 로 간다.
  - Front Controller가 그 요청을 적절한 컨트롤러나 view에 전달한다.
  - 공통 기능은 Front Controller에 작성

4. Dispatcher Servlet

  - 모든 요청을 Dispatcher Servlet이 받는다.
  - URL에 맞는 적절한 컨트롤러 메서드를 식별해서 실행시킨다. (ex.gotoLoginPage)
  - 컨트롤러 메서드는 모델과 뷰 이름을 Return한다. (model, login)
  - Dispatcher Servlet는 ViewResolver를 사용하여 Controller method에게 받은 뷰 이름으로 알맞는 뷰를 찾는다. (.jsp)
  - jsp에 있는 코드를 실행하여 랜더링한다.
  - Response
    
    ~~~java
    @RequestMapping("login")
    public String gotoLoginPage(@RequestParam String name , ModelMap model){
        //jsp 파일로 전달해주기
        model.put("name", name);
        logger.debug("RequestParam is {}" , name);
        return "login";
    }
    ~~~


# 요청 vs 세션의 범위

-----

브라우저에서 오는 모든 요청은 서버에 배포된 웹 어플리케이션에 의해 처리된다.

1. 요청 (Request)

    - 요청이 가진 속성은 한 요청에서만 유효하다. 
    - ex ) LoginController - name / password


2. 세션 (Session)

    - 저장하고 싶은 정보를 다수의 요청에 걸쳐 저장할 수 있다.
    - @SessionAttributes
    - 추가로 메모리를 차지하기 때문에 필요없는건 삭제해주는것이 좋다.



- /META-INF/resources/webjars/bootstrap/5.1.3/css/bootstrap.min.css
- /META-INF/resources/webjars/bootstrap/5.1.3/js/bootstrap.min.js
- /META-INF/resources/webjars/jquery/3.6.0/jquery.min.js

# Spring MVC를 이용한 검증 구현

----------

1. 검증과 관련된 starter project import (spring-boot-starter-validation)
2. Command Bean or Form Backing Object 라는 개념을 사용

    - Command Bean
    ~~~java
   @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String showNewTodoPage(ModelMap model, Todo todo) { //name 값을 가져오기 위해 사용
        String username = (String) model.get("name");
        todoService.addTodo(username, todo.getDescription(), LocalDate.now().plusYears(1), false);
        return "redirect:list-todos";
    }
   ~~~
    - Form Backing Object
   ~~~xml
    <form:form method="post" modelAttribute="todo">
            Description : <form:input type="text" path="description"/>
            <form:input type="hidden" path="done"/>
            <form:input type="hidden" path="id"/>
            <input type="submit" class="btn btn-success"/>
    </form:form>
   ~~~

    - 2-way binding (todo.jsp & TodoController.java)
    
3. Bean에 검증을 추가
   
    - Todo.java
4. 검증 오류를 View에 표시
    
    - todo.jsp

# Spring Boot Auto Configuration - Data JPA

- JPA 와 H2 의존성을 추가했을때

  - JPA / Spring Data JPA 프레임워크 초기화
  - in memory Database(H2) 가 실행됨
  - 어플리케이션 - 데이터베이스 연결됨
. 
- H2 : 인메모리 데이터배이스
  
    - 데이터가 유지되지않는다 (서버를 시작할때마다 초기화된다)


# Docker

----------

- MYSQL 컨테이너 만들기

~~~shell
docker run --detach 
--env MYSQL_ROOT_PASSWORD=dummypassword  
--env MYSQL_USER=todos-user #database 사용자
--env MYSQL_PASSWORD=dummytodos #database의 비밀번호
--env MYSQL_DATABASE=todos  #데이터베이스 연결
--name mysql #이름 지정
--publish 3306:3306 mysql:8-oracle #3306포트에 게시
~~~