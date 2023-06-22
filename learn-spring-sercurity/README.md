# 인증 / 인가


-------

- 인증(Authentication) : 적절한 사용자인지를 판단

    - userId/password
    - Biometrics

- 인가(Authorization) : 사용자의 엑세스 권한 확인


# 보안의 6원칙

-----


1. 아무도 신뢰하지 않는다.

    - 시스템에 들어오는 모든 정보를 검증
2. 최소 권한 할당
    
    - 시스템 설계를 시작할 때 처음부터 보안에 대해 구성해야한다.
    - 각 사용자에게 필요한 사용자 역할과 엑세스 권한을 명확히 정해야 한다.
    
    - 모든 레벨에서 가능한 한 최소한의 권한을 할당하는것이 좋다.

3. 완벽한 조율 구축

    - 모든 사람이 하나의 보안 필터를 거치도록 한다.
    - 요청이 들어올 떄마다 엑세스 권한을 확인해야한다.
4. 심층적인 방어 구축
    
    - 보안을 여러 층으로 구축해야한다. (전송 레이어 , 네트워크 레이어 , 인프라 , 운영 체제 레벨.. )
   
5. 보안 아키텍처는 간단하게

    - 보안 메커니즘의 효율성

6. 설계의 개방성을 보장한다.

    - 개방형 보안 설계를 구축하고 개방형 보안 표준을 사용해야 한다. (ex. JWT)


# Spring Security Layer

---------


Request -> **Spring Sercurity** -> Dispatcher Servlet -> Controller    



# Spring Security filter chain이 제공하는 기능

---------------

1. Authentication : 인증된 유저인지?
2. Authorization : user가 올바른 접근 권한을 가지고있는지?
3. Other : 
   
   - CorsFilter
   - CsrfFilter(Csrf - 사이트 간 요청 위조) : 사용자가 세션에 정보를 담은 채로 악성 브라우저로 이동하여 정보를 탈취당하는것을 방지
   - ExceptionTranslationFilter : 인증과 관련된 예외가 발생할때마다 사용자에게 적절한 HTTP 응답으로 전달


이러한 필터들이 지정한 순서대로 실행된다.

1. Basic Check Filters - CORS , CSRF, ...
2. Authentication Filters
3. Authorization Filters



# Spring Security - Form 인증


----------

Spring Security 는 기본적으로 Form 인증을 사용한다. (username, password)    

- Form 인증 절차


   1. username, password 로그인
   2. Session Cookie가 생성된다.
   3. 이후 브라우저에서 시작되는 요청에는 이 세션 쿠키가 함께 전송된다. (인증된 사용자라는것을 알수있다.)
   

# CSRF


-----------

1. 사용자가 웹사이트에 로그인
2. 로그아웃 하기 전 , 악성 사이트로 이동 
3. 악성 사이트는 남아있는 쿠키를 이용하여 사용자 대신 요청을 실행할 수 있다.  
**세션을 사용하지 않는다면 csrf는 고려하지 않아도 된다.** -> 단순한 REST api 개발할때는 csrf설정을 해제해도 된다.


- 보호하는 방법

   - 동기화 토큰 패턴
  
      - 요청마다 토큰 생성
      - 사용자가 탐색하는 페이지 , 수행하는 작업 마다 새 토큰을 생성한다.
      - Post / Put 같은 업데이트가 있을때 이전 요청에서 생성된 토큰으로 인증한다.

         - 웹페이지 : Form 태그를 사용하면 자동으로 csrf 토큰을 생성해서 보낸다
         - REST API : CSRF Token을 확인하여 요청을 보낼때 담아 보낸다.
   - SameSite Cookie 
  
      - 쿠키를 일정한 사이트로만 전송 할 수 있게 한다.
         ~~~properties
        server.servlet.session.cookie.same-site=strict #properties파일에서 설정방법
        ~~~
        



- 상태를 사용하지 않는 REST API라면, CSRF 사용 해제한다.


# CORS

----------

- 브라우저에서 현재 오리진 외부에 있는 Ajax 호출을 보내는것을 허용하지 않는다.
- 허용 오리진을 설정해줄수있다.

   - 글로벌 설정 : 모든 컨트롤러, 모든 리소스에 적용된다. addCorsMapping 콜백 메서드로 설정한다.
   - 로컬 설정 : 특정 메서드나 특정 컨트롤러 클래스에 @ClassOrigin 설정 
  
      - 기본값은 모두 허용 
      - @ClassOrigin(origins="http://www.~~") 로 허용 오리진 설정해줄수도있음

        
# 인코딩 , 해싱 , 암호화 

------


- 인코딩

    ~~~
    데이터를 원래 형식에서 다른 형식으로 변환하는 것
  ~~~

    - 데이터를 효율적으로 전송하거나 저장하는것이 목적이다.
    - 따라서 암호화 / 복호화 하는데 키가 필요하지 않다.
    - ex) Base 64 , Wav, MP3 ..
 

- 해싱

    ~~~
    데이터를 해시 문자열로 변환
    ~~~
  
    - 단방향 프로세스 , 비가역적이다. -> 본래 데이터를 구할 수 없다.
    - 데이터 무결성을 검증하는데 사용한다. 
    - 본 데이터와 해싱된 데이터를 함께 전송하면, 수신 측에서 해싱(본데이터) <-> 해싱된 데이터를 비교한다.
    - password를 저장할때 많이 사용된다 ( 비가역적이라 보안에 이점이 있다 )
      
        - 사용자가 로그인하면 패스워드를 해싱하여 DB 의 해싱된 패스워드와 비교한다.
    - ex) dcrypt , scrypt


- 암호화

    - 키 / 패스워드가 있어야 복호화 할 수 있다. 

    - ex ) RSA


# Spring Security에서의 Password 처리


------

1초를 work factor로 정하여 단방향 함수를 사용한다.(해싱)        


- work factor : 시스템에서 패스워드를 확인하는데 걸리는 시간 (패스워드를 해싱하고 저장된 값과 비교하는데 걸리는 시간)

- Spring의 PasswordEncoder : 단방향으로 패스워드 변환을 수행하는 인터페이스 

    - 주의할 점

        - 이름이 Encoder이지만, 단방향 알고리즘이다. 인코딩처럼 실제 텍스트를 구할수 있는것이 아니다.
        - PasswordEncoder = BCryptPasswordEncoder



