# Apache Maven

-------


~~~
소프트웨어 관리 및 이해 툴 , 프로젝트 객체 모델 개념을 바탕으로 중앙정보를 활용해 프로젝트 빌드와 문서화를 가능하게 한다.
~~~

### pom.xml 에 있는 정보들

 
1. Maven dependencies : 프로젝트에 사용하는 프레임워크와 라이브러리(일반 의존성 , 전이 의존성)

2. Parent Pom : spring-boot-starter-parent : 다양한 의존성 + Java 버전 설정 제공

   spring-boot-dependencies > spring-boot-starter-parent > 일반 의존성들 