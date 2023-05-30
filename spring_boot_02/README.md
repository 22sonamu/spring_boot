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

### Q5. Spring이 객체를 생성하게 하는것을 쉽게 해줄까? 어렵게 만들까? 