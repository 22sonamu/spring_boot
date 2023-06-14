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

# REST 응답 상태 종류

-------

1. 404 : 리소스를 찾지 못함
2. 500 : 서버에서 Exception
3. 400 : 검증 에러
4. 200 : 성공
5. 201 : post로 새 리소스 생성했을때
   
  ~~~java
  @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){
            service.save(user);
            return ResponseEntity.created(null).build();
    }
  ~~~
6. 204 : 리소스를 업데이트하기위해 put 을 했는데 해당 리소스가 없을때
7. 401 : 사용자가 요청에 올바른 정보를 넣지 않았을때


# Swagger / Open Api 

---------

- 2011 : Swagger Specification / Swagger Tools 도입

  - 이 시기에 많은 REST API를 구축하기 시작했고, 이에 따라 REST API를 문서화 할 수 있는 형식이 필요했다.

- 2016 : Swagger Specification을 기반으로 OPEN API 사양이 만들어졌다.

- open api

```json
{
  "openapi" : "3.0.1",
  "info" : {
    "title" : "OpenAPI definition",
    "version" : "v0"
  },
  "servers" : [ //서버 노출 위치
    {
      "url" : "http://localhost:8080", 
      "description" : "Generated server ual"
    }
  ],
  "paths" : { //리소스에 관한 세부 정보
    "/users": {
      "get": {
        "tags": [
          "user-resource" //메소드
        ],
        "operationId": "retrieveAllUsers",
        "responses": {
          "200": { //예상응답
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/User"
                  }
                }
              }
            }
          }
        }
      }
    }
  },
  "components" : { //선언된 객체
    "schemas" : {
      "User": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int32"
          },
          "name": {
            "maxLength": 2147483647,
            "minLength": 2,
            "type": "string"
          },
          "birthDate": {
            "type": "string",
            "format": "date"
          }
        }
      }
    }
  }
}
```

# Content Negotiation

--------

- 보통 같은 리소스는 같은 URI를 가진다. 
- 그러나 여러 표현이 가능하다 (JSON , XML ... 혹은 English, Korean..)
- 이 경우 소비자는 자신이 원하는 표현을 설정해야한다.
  
  - Accept headet (ex. applcation/xml, application/json....)
  - Accept-Language header

- jackson-dataformat-xml
~~~xml
<dependency>
  <groupId>com.fasterxml.jackson.dataformat</groupId>
  <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
~~~

이 의존성을 추가하기만 하면 사용자는 Header에 Accept : 원하는 응답 형식 을 지정할 수 있다.


# Internationalization - i18n

---------

- HTTP Request Header 사용 ( Accept-Language )
- ex. en - English , nl - 네덜란드어 ....



# Rest Api version 관리

---------

- 응답의 구조를 변경한다면, 모든 소비자에게 영향을 끼칠 수 있다.


- Options

  1. URI Versioning
    
    - http://localhost:8080/v1/person
    - http://localhost:8080/v2/person

  2. Request Parameter versioning

    - http://localhost:8080/person?version=1
    - http://localhost:8080/person?version=2

  3. (Custom) headers versioning
  
    - SAME-URL headers=[X-API-VERSION=1]
    - SAME-URL headers=[X-API-VERSION=2]

  4. Media type versioning

    - SAME-URL produces=application/vnd.company.app-v1+json
    - SAME-URL produces=application/vnd.company.app-v2+json

- 고려해야할 것들
  
  1. URI Pollution
     
    - 1,2번 방법은 url을 새로 만들어야해서 uri pollution이 발생

  2. HTTP header 오용
     
    - 3,4번 방법은 header를 오용하는것이다.

  3. Caching
  
    - Caching은 일반적으로 URL을 기반으로 수행된다.
    - 3,4번 방법은 다른 버전이지만 같은 url을 쓰므로 url을 기반으로 캐싱을 할수 없다.
    - 캐싱을 할때 헤더를 살펴봐야한다.

  4. 브라우저에서 요청을 실행 할 수 있는지?
    
    - 1,2번 방법은 브라우저에서 실행해볼수있다. 그러나 3,4번은 Header를 설정해야 해서 브라우저로 접근 불가능하다.

  5. API Documentation
  
     - 1,2번은 버전 관리에 대한 API문서를 만들기 쉽다. (URL이 다르기 때문)
     - API문서 생성 툴은 헤더를 기준으로 구분하는 문서의 생성을 지원하지 않을 수 있다.

**가장 적절한 방법을 선택해서 , 일관되게 버전 관리 하는것이 가장 중요하다.**