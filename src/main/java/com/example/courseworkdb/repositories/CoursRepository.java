package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoursRepository extends JpaRepository<Cours, Integer> {
    @Override
    void deleteById(Integer integer);

    @Override
    <S extends Cours> S save(S entity);

    @Override
    Optional<Cours> findById(Integer integer);

    @Override
    List<Cours> findAll();

    List<Cours> findByStudents_IdNot(Integer id);

    List<Cours> findByStudents_IdNotOrderByCourseNameAsc(Integer id);

}