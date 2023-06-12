package com.in28minutes.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    private TodoService todoService;



    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        List<Todo> todos = todoService.findByUsername("in28minutes");
        model.addAttribute("todos", todos);

        return "listTodos";
    }

    @RequestMapping(value = "add-todo" , method = RequestMethod.GET)
    public String showTodoPage(){
        return "todo";
    }

    @RequestMapping(value = "add-todo" , method = RequestMethod.POST)
    public String showNewTodoPage(){
        // 투두list를 설정해주는 중복 코드를 없애기 위해 list-todos url로 바로 리디렉션한다.
        return "redirect:list-todos";
    }


}
