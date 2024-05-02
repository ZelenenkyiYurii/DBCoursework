package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.StudentGroup;
import com.example.courseworkdb.services.StudentGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentGroupServiceImpl implements StudentGroupService {
    @Override
    public List<StudentGroup> getAll() {
        return null;
    }

    @Override
    public Optional<StudentGroup> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void create(StudentGroup obj) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(int id, StudentGroup obj) {

    }
}
