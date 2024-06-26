package com.naes.todo_web_app.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    TodoRepository todoRepository;

    Logger log = LoggerFactory.getLogger(TodoController.class);

    public TodoController(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/count")
    int count(){
        return todoRepository.getCount();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<Todo> getAll(){
        return todoRepository.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Todo todo){
        todoRepository.create(todo);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    void update(@PathVariable int id, @RequestBody Todo todo){
        todoRepository.update(id, todo);
        log.info("todo status updated!");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        todoRepository.deleteById(id);
        log.info("todo with id: {} has been deleted!", id);
    }
}
