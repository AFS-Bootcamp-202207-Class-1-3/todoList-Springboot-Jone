package com.example.todolist.service.impl;

import com.example.todolist.Entity.Todo;
import com.example.todolist.exception.NotFoundException;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.service.TodoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Resource
    TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo create(Todo todo) {
        todo.setDone(false);
        return todoRepository.save(todo);
    }

    @Override
    public void deleteById(Integer id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Todo updateById(Integer id, Todo toEntity) {
        Todo oldTodo = todoRepository.findById(id).orElseThrow(() ->new NotFoundException(Todo.class.getSimpleName()));
        oldTodo.merge(toEntity);
        return todoRepository.save(oldTodo);
    }
}
