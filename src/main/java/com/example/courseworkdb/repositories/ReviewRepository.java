package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Override
    <S extends Review> S save(S entity);

    @Override
    List<Review> findAll();

    @Override
    Optional<Review> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}