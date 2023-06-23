package com.in28minutes.learnspringsercurity.resources;

import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER') and #username == authentication.name") //pathVariable = 인증에 잇는 이름과 매칭해야함 (in28minutes)
    @PostAuthorize("returnObject.username =='in28minutes'")
    @RolesAllowed({"ADMIN", "USER"})
    @Secured({"ADMIN", "USER"})
    public Todo retrieveTodosForSpecificUser(@PathVariable String username){
        return TODOS_LIST.get(9);
    }

    @PostMapping("/users/{username}/todos")
    public void createTodosForSpecificUser(@PathVariable String username, @RequestBody Todo todo){ //기본 인증해도 동작하지 않는다. (csrf 토큰이 있어야한다.)
        logger.info("Create {} for {}", todo, username);
    }
}

record Todo(String username, String description){}
