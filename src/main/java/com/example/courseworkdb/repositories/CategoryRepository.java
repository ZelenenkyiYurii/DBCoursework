package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Override
    void deleteById(Integer integer);

    @Override
    List<Category> findAll();

    @Override
    Optional<Category> findById(Integer integer);

    @Override
    <S extends Category> S save(S entity);
}