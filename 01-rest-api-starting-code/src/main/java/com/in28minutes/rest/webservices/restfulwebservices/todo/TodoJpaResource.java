package com.in28minutes.rest.webservices.restfulwebservices.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJpaResource {

    private TodoService todoService;

    private TodoRepository todoRepository;
    public TodoJpaResource(TodoService todoService, TodoRepository repository){
        this.todoService = todoService;
        this.todoRepository=repository;
    }
    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username , @PathVariable int id){
        return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username , @PathVariable int id){
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username , @PathVariable int id , @RequestBody Todo todo){
        //todoService.updateTodo(todo);
        todoRepository.save(todo); //save는 insert와 save 두가지 기능에 다 사용할 수 있다.

        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username , @RequestBody Todo todo){
        todo.setUsername(username);
        todo.setId(null); //새로운 등록이라는 뜻
        todoRepository.save(todo);
        return todo;
    }
}
