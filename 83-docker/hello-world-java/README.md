# Dockerfile Examples

## Docker commands
- docker build -t in28min/hello-world-docker:v1 .


## Dockerfile - 1 - Creating Docker Images

```
FROM openjdk:18.0-slim
COPY target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]
```

- FROM : base image 생성
- COPY : 로컬(target/*.jar)에서 파일을 가져와서 특정 이름(app.jar) 을 붙여 이미지로 가져온다.
- EXPOSE : 컨테이너가 런타임에서 수신하도록 설정된 포트에 대해 Docker에 알린다.
- ENTRYPOINT : 어플리케이션에서 실행될 명령어
## Dockerfile - 2 - Build Jar File - Multi Stage
```
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:18.0-slim
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]

```
Open JDK + maven    


jar 파일을 첫번째 단계의 일부로 빌드하고(FROM maven:3.8.6-openjdk-18-slim AS build)    
이것을 복사해서 두번째 이미지인 app.jar로 가져간다 (COPY --from=build /home/app/target/*.jar app.jar)     

장점 : 로컬 머신에 빌드된 어느것도 사용하지 않는다.   
단점 : 빌드 시간이 오래 걸린다    
원래는 로컬에서 빌드 후 -> jar 파일을 copy해서 옮겼지만, Muli Stage를 이용하면 컨테이너에서 빌드를 진행한다.


## Dockerfile - 3 - Caching 


------

2번에서 빌드 시간을 단축시키는 방법이다.

```Dockerfile
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/in28minutes/rest/webservices/restfulwebservices/RestfulWebServicesApplication.java	/ 
/home/app/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/RestfulWebServicesApplication.java

RUN mvn -f /home/app/pom.xml clean package # 가장 시간이 오래 걸림(모든 의존성 다운로드)

COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:18.0-slim
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
```

- pom.xml 과 Spring boot Application을 복사한다.(거의 변경되지 않는 소스들)
- mvn clean package 실행
- 모든 파일을 다시 복사한다.
- mvn clean package 실행

**pom.xml이나 Application 코드가 변경되지 않는다면 , 처음의 5줄 레이어가 다시 사용되어 시간을 단축할 수 있다. (가장 시간이 오래 걸리는 절차를 재사용할수있음)**


- 첫번째 이미지 생성 로그
~~~shell
 => [build 1/7] FROM docker.io/library/maven:3.8.6-openjdk-18-slim@sha256:c7c88cee48948f5b2097c0bba1c05d0666f9655604bb98bb1899e6d95aefbfb4                                                 0.0s            167.2s
 => CACHED [stage-1 1/2] FROM docker.io/library/openjdk:18.0-slim@sha256:8e17383576d7e71988ee5927473a32e8461381c7a29eefa9a0c24b3a28926272                                                  0.0s
 => [internal] load build context                                                                                                                                                          0.0s
 => => transferring context: 11.44kB                                                                                                                                                       0.0s            167.2s
 => CACHED [build 2/7] WORKDIR /home/app                                                                                                                                                   0.0s
 => [build 3/7] COPY ./pom.xml /home/app/pom.xml                                                                                                                                           0.0s
 => [build 4/7] COPY ./src/main/java/com/in28minutes/rest/webservices/restfulwebservices/RestfulWebServicesApplication.java /home/app/src/main/java/com/in28minutes/rest/webservices/rest  0.0s
 => [build 5/7] RUN mvn -f /home/app/pom.xml clean package                                                                                             167.2s
 => [build 6/7] COPY . /home/app                                                                                                                                                           0.1s
 => [build 7/7] RUN mvn -f /home/app/pom.xml clean package      
~~~

- 레이어 5에서 가장 많은 시간이 소요된다.
- 레이어 7은 레이어 5에서 의존성을 다 받아두었기 때문에 적은 시간이 걸린다.


- Controller 변경 후 , 두번째 이미지 생성


~~~shell
 => [build 1/7] FROM docker.io/library/maven:3.8.6-openjdk-18-slim@sha256:c7c88cee48948f5b2097c0bba1c05d0666f9655604bb98bb1899e6d95aefbfb4                                                 0.0s
 => CACHED [stage-1 1/2] FROM docker.io/library/openjdk:18.0-slim@sha256:8e17383576d7e71988ee5927473a32e8461381c7a29eefa9a0c24b3a28926272                                                  0.0s
 => [internal] load build context                                                                                                                                                          0.0s
 => => transferring context: 13.47kB                                                                                                                                                       0.0s              0.0s
 => CACHED [build 2/7] WORKDIR /home/app                                                                                                                                                   0.0s
 => CACHED [build 3/7] COPY ./pom.xml /home/app/pom.xml                                                                                                                                    0.0s
 => CACHED [build 4/7] COPY ./src/main/java/com/in28minutes/rest/webservices/restfulwebservices/RestfulWebServicesApplication.java /home/app/src/main/java/com/in28minutes/rest/webservic  0.0s
 => CACHED [build 5/7] RUN mvn -f /home/app/pom.xml clean package                                                                                              0.0s
 => [build 6/7] COPY . /home/app                                                                                                                                                           0.1s
 => [build 7/7] RUN mvn -f /home/app/pom.xml clean package                                                                                                                                 8.3s
 => [stage-1 2/2] COPY --from=build /home/app/target/*.jar app.jar    
~~~

- 5레이어까지 이미지가 같이 때문에 이미지 생성 시간을 단축할 수 있다.