package com.in28minutes.springboot.myfirstwebapp.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> { //<관리할 객체 , id의 데이터타입>

        public List<Todo> findByUsername(String Username);

}
