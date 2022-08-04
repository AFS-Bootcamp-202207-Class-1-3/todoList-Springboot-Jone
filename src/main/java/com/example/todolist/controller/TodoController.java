package com.example.todolist.controller;

import com.example.todolist.Entity.Todo;
import com.example.todolist.dto.TodoRequest;
import com.example.todolist.mapper.TodoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import com.example.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("todos")
public class TodoController {
    @Resource
    TodoService todoService;

    @Resource
    TodoMapper todoMapper;

    @GetMapping
    public List<Todo> getAll() {
        return todoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@RequestBody TodoRequest todoRequest){
        return todoService.create(todoMapper.toEntity(todoRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        todoService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Todo updateById(@PathVariable Integer id, @RequestBody TodoRequest todoRequest){
        return todoService.updateById(id, todoMapper.toEntity(todoRequest));
    }

}