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


# HATEOAS

---------

```text
Hypermedia as the Engine of Application State.
REST Api를 사용하는 클라이언트가 전적으로 서버와 동적인 상호작용이 가능하도록 하는 것.
```

- 클라이언트가 서버로부터 어떠한 요청을 할 때, 요청에 필요한 URI를 응답에 포함시켜 반환한다.

- Option

  1. 사용자 정의 형식 설계하여 구현한다.
  2. 표준 형식으로 구현한다. 
     
    - HAL(JSON Hypertext Application Language) : API의 리소스에 하이퍼링크를 생성하는 일관적이고 쉬운 방법을 제공하는 형식
    - Spring HATEOAS를 사용하면된다.

  ```json
  {
    "id": 2,
    "name": "Zaza",
    "birthDate": "1998-06-14",
    "_links": {
        "all-users": {
            "href": "http://localhost:8080/users"
        }
    }
  } 
  ```
  

# Customizing REST API

------
- 직렬화

  - Jackson (most popular json 직렬화 프레임워크)


1. field name을 Customize

  - @JSONProperty

2. 선택된 필드만 반환하기

  - Filtering

    - 정적 필터링 : 여러 api에서 다 필터링됨 (@JsonIgnore , @JsonIgnoreProperties())
    - 동적 필터링 : api에 특성에 띠라 필터링 여부를 결정할수있음

# Spring Boot Actuator 

-------

- Bean

ex.
```json
"helloWorldController" : {
  "aliases": [],
  "scope": "singleton", 
  "type": "com.in28minutes.rest.webservices.restfulwebservices.helloworld.HelloWorldController",
  "resource": "file [/Users/bccard9503/Desktop/git/spring_boot/restful-web-services/target/classes/com/in28minutes/rest/webservices/restfulwebservices/helloworld/HelloWorldController.class]", //리소스의 위치
  "dependencies": [
    "messageSource" //의존성
  ]
}

```

- metrics


ex. http://localhost:8080/actuator/metrics/http.server.requests

~~~json
{
  "name": "http.server.requests", //http 요청 수에 관련한 세부 정보
  "baseUnit": "seconds",
  "measurements": [
    {
      "statistic": "COUNT",
      "value": 4  //4번 요청됨
    },
    {
      "statistic": "TOTAL_TIME",
      "value": 0.07901487600000001
    },
    {
      "statistic": "MAX",
      "value": 0.034623042
    }
    ]
}
~~~

- mappings

ex.HelloWorldController


```json
{
  "handler": "com.in28minutes.rest.webservices.restfulwebservices.helloworld.HelloWorldController#helloWorldVariable(String)",
  "predicate": "{GET [/hello-world/path-variable/{name}]}", //Get , URI 
  "details": {
  "handlerMethod": {
    "className": "com.in28minutes.rest.webservices.restfulwebservices.helloworld.HelloWorldController",
    "name": "helloWorldVariable",
    "descriptor": "(Ljava/lang/String;)Lcom/in28minutes/rest/webservices/restfulwebservices/helloworld/HelloWorldBean;"
  },
  "requestMappingConditions": {
    "consumes": [],
    "headers": [],
    "methods": [
      "GET"
    ],
    "params": [],
    "patterns": [
      "/hello-world/path-variable/{name}"
    ],
    "produces": []
    }
  }
}
```


# HAL Explorer

------


- HAL 

```text
  JSON Hypertext Appplication Launguage 
  일관되고 쉽게 api 리소스 간 하이퍼링크를 제공
```

ex. HATEOAS

```json
{
  "id": 1,
  "user_name": "Mara",
  "birth_date": "1999-06-15",
  //_links에서 사용한 format = HAL
  "_links": {
    "all-users": {
      "href": "http://localhost:8080/users"
    }
  }
}
```

- HAL Explorer

```text
  HAL을 이용하는 RESTful 하이퍼미디어 API를 탐색하는 API탐색기
```
  - 장점 : 비전문가도 API를 사용할 수 있다.


- Spring Boot HAL Explorer

  - Spring Boot는 HAL Explorer를 자동 설정해준다.





# User / Post 관계 연결

---------

  User.java

~~~java
    @OneToMany(mappedBy = "user") //user 기준으로 post는 여러개
    @JsonIgnore
    private List<Post> posts;
~~~

  Post.java
~~~java
    @ManyToOne(fetch = FetchType.LAZY) 
    @JsonIgnore
    private User user;
~~~


~~~shell
  Hibernate: create sequence post_seq start with 1 increment by 50
  Hibernate: create sequence user_details_seq start with 1 increment by 50
  Hibernate: create table post (id integer not null, user_id integer, description varchar(255), primary key (id)) //user이랑 post를 1:N 관계로 연결 , fk인 user_id가 자동으로 생성되었다.
  Hibernate: create table user_details (birth_date date, id integer not null, name varchar(255), primary key (id))
  Hibernate: alter table if exists post add constraint FKa3biitl48c71riii9uyelpdhb foreign key (user_id) references user_details
~~~


# jpa optional 사용법

### optinal class란?

Optional이란 **null일수도 있는 객체**를 감싸는 일종의 Wrapper class 이다.

~~~java
  Optional<T> optional
~~~

### Optional 객체 생성

1. Optional.empty() : 비어있는 Optional 객체를 생성한다. 따라서 내부에 있는 Member 객체는 null이고, Optional 내부적으로 미리 생성해놓은 Singleton 인스턴스이다.

~~~java
  Optional<Memver> optMember = Optional.empty();
~~~

2. Optional.if(value) : 인자 value를 담고 있는 Optional 객체로 생성한다. 만약 넘긴 객체가 null이라면 NullPointExvception이 발생한다.

~~~java
  Optional<Memeber> optMember = Optional.ofNullable(member);
~~~

3. Optional.ofNullable(value) : null일수도 있고 아닐수도있는 객체를 담아 Optional 객체로 생성한다. null인지 아닌지 확신이 서지 않는 객체를 생성할 때 쓴다.

~~~java
  Optional<Member> optMember = Optional.ofNullable(member);
~~~


### Optional 객체에 접근하는 법

1. get()  
    Optional 내부에 담긴 객체를 반환한다.  
    만약 null인 객체라면 NoSuchElementException이 발생한다. 따라서 분기처리가 필요하다. 

~~~java
    Memeber member = optMember.get();
    if(member.isPresent()){
        
    }
    else{
        
    }
~~~

2. orElseThrow(Supplier<? extends X> exceptionSupplier)   

    Optional 내부에 담긴 객체가 null이 아니면 담겨 있는 객체를 반환하고, null이라면 지정된 에러를 발생시킨다.

~~~java
  Member member = optMember.orElseThrow(NullPointerException::new);
~~~

3. orElse(T other)   
  
    Optional 내부에 담긴 객체가 null이 아니라면 담겨있는 객체를 반환하고, null이라면 인자로 넘겨준 member1이라는 객체를 반환한다.   
    Optional 내부 객체의 null 여부와 상관 없이 , member1 객체는 생성된다.

~~~java
  Member member = optMember.orElse(member1);
~~~

4. orElseGet(Supplier<? extends T> other) 

   Optional 내부에 담긴 객체가 null이 아니라면 담겨있는 객체를 반환하고, null이라면 인자로 넘겨준 함수형 인자를 통해 생성된 객체를 반환한다.   
   Optional 내부의 객체가 null이 아니랴면, member1객체는 생성되지 않는다.

# java Predicate

-----------

### Java Predicate란?

  Predicate Interface는 Java에서 함수형 프로그래밍을 구현하기 위해 Java 버전 1.8부터 도입된 함수형 인터페이스로 제네릭 타입인 한 개의 매개변수를 전달받아 특정 작업을 수행 후 Boolean 타입의 값을 반환하는 작업을 수행할 때, 사용된다.


  

### Predicate 객체 생성

~~~java
  Predicate<Integer> predicate = (num) -> num > 10;
~~~

### 4가지 메서드

1. test()  
     제네릭 타입인 매개변수를 전달받아 특정 작업을 수행 후 Boolean 타입의 값을 반환한다.

~~~java
  Predicate<Integer> predicate = (num) -> num > 10;
  boolean result = predicate.test(100);
~~~

2. and()

    test() 메서드 반환 결과와 and() 메서드의 매개변수로 전달된 Predicate 객체의 test() 메서드 반환 결과에 대해 and 연산을 수행한다.
~~~java
  Predicate<Integer> predicate1 = (num) -> num > 10;
  Predicate<Integer> predicate2 = (num) -> num < 20;
  
  boolean result = predicate1.and(predicate2).test(25);
~~~


3. negate() 

    매개변수를 가지지 않으며, test() 메서드의 반환 결과를 부정한다

4. or()

    test() 메서드 반환 결과와 or() 메서드의 매개변수로 전달된 Predicate 객체의 test() 메서드 반환 결과에 대해 or 연산을 수행한다.


5. isEqual()

    인자로 전달된 객체와 같은지 비교하는 Predicate 객체를 리턴한다.  
    Stream에서 사용될 수도 있으며, Stream으로 전달되는 데이터가 특정 값과 같은지 비교할 때 사용할 수 있다.

    ~~~java
      Stream<Integer> stream = IntStream.range(1, 10).boxed();

      stream.filter(Predicate.isEqual(5)).forEach(System.out::println);
   ~~~
  

- Stream + Predicate 활용 예제

~~~java
  List<String> names = Arrays.asList("James", "Alex", "Alberto", "Robert");
  Predicate<String> startsWithPredicate = str -> str.startsWith("A");
  Predicate<String> lengthPredicate = str -> str.length() >= 5;

  List<String> result = names.stream()
    .filter(startsWithPredicate.or(lengthPredicate))
    .collect(Collectors.toList());
~~~
