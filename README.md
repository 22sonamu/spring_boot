# 1. BEAN

----------
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

### Q3. Bean list는 Spring에서 어떻게 관리되는지?

### Q4. 여러가지 Bean이 사용가능하다면 ?

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
