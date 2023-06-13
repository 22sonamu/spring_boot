package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

    public TodoControllerJpa(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    private TodoRepository todoRepository;

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        //세션으로부터 name을 받으면 , 세션 등록 안된경우 null이 넘어온다 -> Spring security에서 직접 받아오는게 좋다.
        String username = getLoggedInUsername();
        List<Todo> todos = todoRepository.findByUsername(username);
        model.addAttribute("todos", todos);

        return "listTodos";
    }

    private String getLoggedInUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showTodoPage(ModelMap model) {
        String username = (String) model.get("name");
        Todo todo = new Todo(0,username, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String showNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) { //name 값을 가져오기 위해 사용
        if(result.hasErrors()){
            return "todo";
        }
        String username = (String) model.get("name");
        todo.setUsername(username);
        todoRepository.save(todo);
        // 투두list를 설정해주는 중복 코드를 없애기 위해 list-todos url로 바로 리디렉션한다.
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String showUpdateTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }


}
