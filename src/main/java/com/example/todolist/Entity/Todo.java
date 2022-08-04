package com.example.todolist.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String context;
    private Boolean done;

    public Todo() {
    }

    public Todo(String context, Boolean done) {
        this.context = context;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void merge(Todo toEntity) {
        if(toEntity.getDone() != null){
            this.setDone(toEntity.getDone());
        }
        if(toEntity.getContext() != null){
            this.setContext(toEntity.getContext());
        }
    }
}
