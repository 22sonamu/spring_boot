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
