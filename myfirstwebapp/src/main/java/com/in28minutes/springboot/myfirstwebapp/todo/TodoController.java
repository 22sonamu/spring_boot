package com.in28minutes.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    private TodoService todoService;


    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername("in28minutes");
        model.addAttribute("todos", todos);

        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showTodoPage() {
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String showNewTodoPage(@RequestParam String description, ModelMap model) { //name 값을 가져오기 위해 사용
        String username = (String) model.get("name");
        todoService.addTodo(username, description, LocalDate.now().plusYears(1), false);
        // 투두list를 설정해주는 중복 코드를 없애기 위해 list-todos url로 바로 리디렉션한다.
        return "redirect:list-todos";
    }


}
