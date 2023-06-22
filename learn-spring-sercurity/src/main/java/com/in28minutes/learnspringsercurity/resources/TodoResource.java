package com.in28minutes.learnspringsercurity.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final List<Todo> TODOS_LIST
            = List.of(new Todo("in28minutes", "learn AWS"), new Todo("in28minutes", "Get AWS Certified"));
    @GetMapping("/todos")
    public List<Todo> retrieveAllTodo(){
        return TODOS_LIST;
    }

    @GetMapping("/users/{username}/todos")
    public Todo retrieveTodosForSpecificUser(@PathVariable String username){
        return TODOS_LIST.get(9);
    }

    @PostMapping("/users/{username}/todos")
    public void createTodosForSpecificUser(@PathVariable String username, @RequestBody Todo todo){ //기본 인증해도 동작하지 않는다.
        logger.info("Create {} for {}", todo, username);
    }
}

record Todo(String username, String description){}
