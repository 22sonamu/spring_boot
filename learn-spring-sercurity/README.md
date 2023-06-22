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






