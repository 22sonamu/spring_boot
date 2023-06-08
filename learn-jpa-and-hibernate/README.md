# JDBC vs Spring JDBC

-------

### JDBC
- 많은 SQL 쿼리를 작성해야한다.
- 그리고 많은 Java 코드를 작성해야한다.

### Spring JDBC

- 많은 SQL 쿼리를 작성해야한다.
- JDBC 보단 적은 java 코드를 작성한다.


~~~h2
insert into course(id, name, author)
values (1, 'Learn AWS', 'in28minutes');

select * from course;

delete from course where id=1
~~~