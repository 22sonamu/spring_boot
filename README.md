# 1. BEAN

----------
### Q1. Spring Container vs Spring Context vs IOC Container vs Application Context ?


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
