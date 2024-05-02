package com.example.courseworkdb.services;

import com.example.courseworkdb.entities.Student;

import java.util.List;
import java.util.Optional;

public interface TemplateService<T> {
    List<T> getAll();
    Optional<T> getById(int id);
    void create(T obj);
    void deleteById(int id);
    void update(int id,T obj);
}
