package com.example.todolist.controller;

import com.example.todolist.Entity.Todo;
import org.springframework.stereotype.Controller;
import com.example.todolist.service.TodoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("todos")
public class TodoController {
    @Resource
    TodoService todoService;

    @GetMapping
    public List<Todo> getAll() {
        return todoService.findAll();
    }


}
