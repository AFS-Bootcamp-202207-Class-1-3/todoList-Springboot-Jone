package com.example.todolist.service;

import com.example.todolist.Entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();
}
