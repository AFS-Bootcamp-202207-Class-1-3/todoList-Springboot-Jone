package com.example.todolist.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String className) {
        super(className + " Not found!.");
    }
}
