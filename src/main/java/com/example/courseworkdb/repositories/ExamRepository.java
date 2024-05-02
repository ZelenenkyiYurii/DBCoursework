package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
    @Override
    void deleteById(Integer integer);

    @Override
    List<Exam> findAll();

    @Override
    Optional<Exam> findById(Integer integer);

    @Override
    <S extends Exam> S save(S entity);
}