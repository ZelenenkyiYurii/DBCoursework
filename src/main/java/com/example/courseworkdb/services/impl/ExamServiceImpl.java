package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Exam;
import com.example.courseworkdb.services.ExamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ExamServiceImpl implements ExamService {
    @Override
    public List<Exam> getAll() {
        return null;
    }

    @Override
    public Optional<Exam> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void create(Exam obj) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(int id, Exam obj) {

    }
}
