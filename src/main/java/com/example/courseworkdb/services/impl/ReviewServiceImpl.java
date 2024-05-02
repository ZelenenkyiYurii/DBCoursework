package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Review;
import com.example.courseworkdb.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ReviewServiceImpl implements ReviewService {
    @Override
    public List<Review> getAll() {
        return null;
    }

    @Override
    public Optional<Review> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void create(Review obj) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(int id, Review obj) {

    }
}
