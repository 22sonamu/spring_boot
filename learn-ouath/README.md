# Docker 


-----


### Docker 이미지가 작동할수있는 이유

- Docker 이미지는 어플리케이션을 실행하는 데 필요한 모든 것을 갖추고있다.

    - 운영 체제
    - 어플리케이션 런타임(ex. JDK , Python , NodeJS)
    - 어플리케이션 코드 , 의존성

### Docker 의 Background에서 일어나는 일

~~~shell
    docker container run -d -p 5000:5000 in28min/hello-world-nodejs:0.0.1.RELEASE
~~~
- Container : running image
- in28min/hello-world-nodejs : Repository name
- 0.0.1.RELEASE : tag(or version)
- p : host port 와 container port(내부 port) 를 매핑하고있다.

    - 내부 포트는 이미지의 개발자만이 변경할 수 있다.