# 1. BEAN 

----------

```
Spring이 관리하는 Java Object
```

### Q1. Spring Container vs Spring Context vs IOC Container vs Application Context ?

------

1. **Spring Container = Spring Context = Spring IOC Container** 
     
    - Spring의 Bean List 관리 
    - Bean의 Lifecycle 관리
    - Spring Container를 생성하기 위한 Input : 생성한 Class들 ex) Person Class, SpringConfiguration Class
    - Output : Ready System
       ```  
        Ready System
            - JVM
                - Spring Context
                    - Bean List ( ex ) name, age, person, person2.... )
        ```
    1. Bean Factory
        - 기본 Spring Container
        - 메모리에 심한 제약이 있는 서비스에 사용
    
    2. Application Context 
        - 전문가용 고급 Spring Container
        - 가장 자주 사용하는 Container
        - Web Service , Rest API 등등에 사용


### Q2. Java Bean vs Spring Bean?

1. **Java Bean**
    ```java
    class JavaBean implements Serializable{ //제약 3. Serializable
        public JavaBean(){ //제약 1. no-arg constructor
            
        }    
        private String text;
        private int number;
        //제약 2. getter / setter (생략)
        
   }
   ```
    - 제한사항
      1. no-arg constructor (기본 생성자)
         - 기본으로 생성되며 , 명시적으로 만들수도있다.
      2. getter and setter
      3. Serializable
2. **Spring Bean**
   - Spring 프레임워크에서 관리하는 모든 Bean

3. **POJO**
    - Java의 모든 Object

### Q3. Spring에서 사용되는 Bean을 나열하려면 ?
~~~java
var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);

Arrays.stream(context.getBeanDefinitionNames())
        .forEach(System.out::println);
~~~
### Q4. 여러가지 Bean이 사용가능하다면 ?

```java
System.out.println(context.getBean(Person.class)); 
```

```logcatfilter
Exception in thread "main" org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.in28minutes.learnspringframework.helloworld.Person' available: expected single matching bean but found 3: person,person2MethodCall,person3Parameters
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveNamedBean(DefaultListableBeanFactory.java:1299)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveBean(DefaultListableBeanFactory.java:484)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:339)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:332)
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1174)
	at com.in28minutes.learnspringframework.helloworld.App02HelloWorldSpring.main(App02HelloWorldSpring.java:37)
```
Person class를 Return하는 Bean을 찾으면 , 위와 같은 에러가 발생한다.
- 해결방법
1. **@Primary** : 우선순위 부여
    ```java
        @Bean
        @Primary //Primary 어노테이션
        public Person person() {
            return new Person(
                    "Ravi",
                    20,
                    new Address("Main Street", "Seoul")
            );
        }
    ```
2. **@Qualifier("이름")** 
    ~~~java
   @Bean(name = "address3")
    @Qualifier("address3qualifier")
    public Address address3() {
        return new Address("을지로3", "서울3");
    }
   
   @Bean
    public Person person5Qualifier(String name, int age, @Qualifier("address3qualifier") Address address) {
        return new Person(name, age, address);
    }
   ~~~

### Q5. Spring이 객체를 만들기 시작하게하려면?

~~~java
@Bean
    public Person person() {
        return new Person(
                "Ravi",
                20,
                new Address("Main Street", "Seoul")
        );
    }
~~~

위 코드를 보면 알수있듯이 , 지금은 내가 코드를 작성하여 객체를 만들고있다.
Spring이 객체를 만들게 하려면 어떻게 해야할까?

---------

1. **@Component**
- @Component로 등록하면 , 자동으로 Spring Bean 관리 목록에 등록된다.
_PacManGame.java_
    ~~~java
    @Component //Spring이 관리할 Component에 등록 
    public class PacManGame implements GamingConsole{
        @Override
        public void up() {
            System.out.println("up");
        }
    
        @Override
        public void down() {
            System.out.println("down");
        }
    
        @Override
        public void left() {
            System.out.println("left");
        }
    
        @Override
        public void right() {
            System.out.println("right");
        }
    }
    ~~~
    
2. **@ComponentScan("패키지 경로")**
- "패키지 경로" 에서 @Component로 관리할 Bean에 등록된 객체들을 탐색한다.

    _App03GamingSpringBeans.java_
    ~~~java
    @Configuration
    @ComponentScan("com.in28minutes.learnspringframework.game") 
    //"com.in28minutes.learnspringframework.game" 에서 컴포넌트를 찾아라
    public class App03GamingSpringBeans {
        
    /*
    아래의 코드를 @Component + @ComponentScan이 대신해준다.        
     */
    //    @Bean 
    //    public GamingConsole game(){
    //        var game = new PacManGame();
    //        return game;
    //    }
    
        @Bean
        public GameRunner gameRunner(GamingConsole game){ 
            //@ComponentScan 어노테이션 덕분에 GamingConsole Bean 을 찾을수있다.
            var gameRunner = new GameRunner(game);
            return gameRunner;
        }
        public static void main(String[] args) {
    
            try(var context = new AnnotationConfigApplicationContext(App03GamingSpringBeans.class)){
                context.getBean(GamingConsole.class).up();
                context.getBean(GameRunner.class).run();
            }
            
        }
    }
    ~~~

### Q5. Spring이 객체를 생성하게 하는것을 쉽게 해줄까? 어렵게 만들까? 

### Q6. Primary vs Qualifier 우선순위
```
Qualifier > Primary
```

# Spring 용어정리

----------

### @Component

Component가 추가된 Class의 인스턴스는 스프링 프레임워크가 관리하게 된다.


### Dependency

ex ) GameRunner는 GamingConsole이 필요하다.

- GamingConsole( ex. MarioGame )은 GameRunner의 의존성(Dependency)이다.

### Component Scan

어노테이션을 사용하여 패키지 이름을 명시

### Dependency Injection
1. Component Scan 하고
2. 컴포넌트의 의존성을 무엇인지 확인하고 Autowiring

### IOC(제어반전)
Dependency Injection을 하면 , 객체 생성 제어의 주체가 프로그래머 -> Spring Framework로 반전된다

### Component 와 Bean 의 차이
1. Where?
   
    - Component : Java class
    - Bean : Spring의 Configuration class 안에 있는 method
   
2. Ease of use

    - Component : Easy . 하나의 어노테이션만 추가하면 된다.
    - Bean : 모든 method에 어노테이션을 추가해야햔다.

3. Autowiring

    - Component : 가능 , Field / Setter / Constructor 3가지 방식으로 가능
    - Bean : 가능 , mothod call 혹은 Parameter로 가능
   
4. Who creates beans?

    - Component : Spring Framework
    - Bean : 프로그래머가 직접 객체 생성
5. Recommended for

    - Component : 일반적으로 권장됨
    - Bean : 객체를 생성하기 전에 여러 사항을 점검해야하는 경우 , 제 3자 라이브러리 Bean을 인스턴스화 하는경우 ( 객체의 class 에 직접 Component 어노테이션을 붙일수 없는 경우 )


# Bean 초기화

---------------


### Eager 초기화가 추천되는 이유
어플리케이션이 시작할 때 오류를 바로 확인할 수 있기 때문이다.

### @Lazy

- 실제 의존성 대신 , Lazy-resolution proxy가 주입된다.

- @Configuration 에도 사용할수있는데 , Configuration class 내의 모든 Bean method가 Lazy 초기화 된다.

### 지연 초기화 vs 즉시 초기화

1. Initilization time
   
    - Lazy : 어플리케이션에서 처음 사용될 때 초기화된다.
    - Eager : 어플리케이션이 처음 실행될 때 초기화된다.

2. 초기화하다가 오류가 발생하면

    - Lazy : 런타임 Exception 이 된다.
    - Eager : 어플리케이션 시작이 안된다.

3. 메모리 사용량
    
    - Lazy : 어플리케이션이 시작될때 모두 초기화되므로 Eager 보다 많이 사용한다.
    - Eager : Lazy 에 비해 덜 사용한다.

4. 추천 상황

    - Lazy : 어플리케이션에 Bean이 아주 드물게 사용될때 좋다.
    - Eager : 대부분의 Bean




### Java Singleton(GOF) vs Spring Singleton

- Spring Singleton : Spring IOC container 하나당 객체 인스턴스가 하나 (scope - 코드 전체)
- Java Singleton : Java 가상 머신 하나당 객체 인스턴스 하나 (scope - 해당 컨테이너 내부)


### Singleton 이란? 
~~~
하나의 오브젝트가 여러개 생기는것을 방지하는 디자인패턴
~~~

빈을 등록할때 아무런 설정을 하지 않으면 싱글톤 scope를 가진다.

### Prototype vs Singleton

1. Instances
   
   - Prototype : 1 IOC Container 에 인스턴스 여러개 가능
   - Singleton : 1 IOC Container <-> 1 인스턴스
2. Beans

   - Prototype : Bean이 참조될때마다 새로운 Bean 생성
   - Singleton : 같은 Bean 이 공유됨

3. Code 

   - Prototype :

   ```java
   @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
   ```
   
   - Singleton : Default

4. 추천상황 
   
   - Prototype : Stateful beans (ex . 사용자 정보 - 개인마다 달라야함)
   - Singleton : Stateless beans

   
# Jakarta EE

------------

1. 초기 자바 버전에서 엔터프라이즈 기능 대부분은 JDK에 구축되어있었다.
2. 시간이 지나면서
   
   - J2EE ( JAVA 2 Platform Enterprise Edition )에 따라 분리되었다.
   - JavaEE ( J2EE가 리브랜딩됨 )
   - JakartaEE ( JavaEE의 소유자가 바뀌면서 개명 )


- 구성
   
   - Jakarta Server Pages (JSP) : 웹 어플리케이션을 만드는데 사용
   - Jakarta Standard Tag Library(JSTL) : 웹 페이지에 동적 정보를 나타내는데 사용하는 라이브러리
   - Jakarta Enterprise Beans (EJB)
   - Jakarta RESTful Web Services : REST 웹 서비스 구축 방법에 대한 규격
   - Jakarta Bean Validation : Bean 유효성 검사
   - Jakarta Contexts and Dependency injection(CDI) : 의존성 주입을 위한 API
   - Jakarta Persistence (JPA)
- 

### CDI

~~~
   의존성 주입을 위한 API
~~~
Inject(Autowired와 비슷), Named(Component와 비슷), Qualifier , Scope , Singleton 같은 의존성 주입용 어노테이션을 포함한다.