package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Category;
import com.example.courseworkdb.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Optional<Category> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void create(Category obj) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(int id, Category obj) {

    }
}
