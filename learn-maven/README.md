# Apache Maven

-------


~~~
소프트웨어 관리 및 이해 툴 , 프로젝트 객체 모델 개념을 바탕으로 중앙정보를 활용해 프로젝트 빌드와 문서화를 가능하게 한다.
~~~

### pom.xml 에 있는 정보들

 
1. Maven dependencies : 프로젝트에 사용하는 프레임워크와 라이브러리(일반 의존성 , 전이 의존성)

2. Parent Pom : spring-boot-starter-parent : 다양한 의존성 + Java 버전 설정 제공

   spring-boot-dependencies > spring-boot-starter-parent > 일반 의존성들 

3. Name of Project : groupId, artifactId


ex. spring boot starter 같은 프로젝트도 group Id 와 artifactId를 가진다.

~~~xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter</artifactId>
</dependency>
~~~

   - groupId : package 이름과 비슷하다.
   - artifactId : class 이름과 비슷하다.

   - 다른 프로젝트에서 우리 프로젝트를 사용할때 이것들을 쓰기 때문에 중요하다.


### Options

ex. Dependency Tree

~~~
[INFO] com.in28minutes:learn-maven:jar:0.0.1-SNAPSHOT
[INFO] +- org.springframework.boot:spring-boot-starter:jar:3.1.1:compile
[INFO] |  +- org.springframework.boot:spring-boot:jar:3.1.1:compile
[INFO] |  |  \- org.springframework:spring-context:jar:6.0.10:compile
[INFO] |  |     +- org.springframework:spring-aop:jar:6.0.10:compile
[INFO] |  |     +- org.springframework:spring-beans:jar:6.0.10:compile
[INFO] |  |     \- org.springframework:spring-expression:jar:6.0.10:compile
[INFO] |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.1.1:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.1.1:compile
[INFO] |  |  +- ch.qos.logback:logback-classic:jar:1.4.8:compile
[INFO] |  |  |  \- ch.qos.logback:logback-core:jar:1.4.8:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.20.0:compile
[INFO] |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.20.0:compile
[INFO] |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.7:compile
[INFO] |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  +- org.springframework:spring-core:jar:6.0.10:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:6.0.10:compile
[INFO] |  \- org.yaml:snakeyaml:jar:1.33:compile
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.1.1:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.1.1:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.1.1:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.8.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.7:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.0:test
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.2:test
[INFO]    +- net.minidev:json-smart:jar:2.4.11:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.4.11:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.3:test
[INFO]    +- org.assertj:assertj-core:jar:3.24.2:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.14.5:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.9.3:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.9.3:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.2.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.9.3:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.9.3:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.9.3:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.9.3:test
[INFO]    +- org.mockito:mockito-core:jar:5.3.1:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.14.5:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.3.1:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.1:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:6.0.10:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.9.1:test
~~~


### 빌드 생명주기


- Validate : 문제가 없나 확인
- Compile : JAVA 코드 컴파일
- Test : 단위 테스트 실행
- Package : Jar 파일 생성
- Integration Test : 통합 테스트
- Verify : 검증
- Install : Jar 파일을 target 폴더에 설치
- Deply : 배포 (배포 설정 반영)


### mvn 명령어

- mvn clean : target 파일삭제
- mvn compile : 소스 파일 컴파일
- mvn test-compile : 소스 파일 + 테스트 파일 컴파일
- mvn test : 유닛 테스트 실행
- mvn package : jar 파일생성
- mvn help : effective-pom 알려줌
- mvn dependency : 의존성 tree

### 스프링 버전 관리 규칙

- version scheme - MAJOR.MINOR.PATCH[-MODIFIER]

   - MAJOR : 업데이트 작업량이 매우 많은 경우
   - MINOR : 업데이트 양이 적은 경우
   - PATCH : 업데이트 작업량 아예 X
   - MODIFIER : RELEASE , MileStone(M1, M2 ...), Release candidates(RC1, RC2...) 등 표시