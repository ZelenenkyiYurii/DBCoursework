package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Integer> {

    @Override
    <S extends StudentGroup> S save(S entity);

    @Override
    List<StudentGroup> findAll();

    @Override
    Optional<StudentGroup> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}