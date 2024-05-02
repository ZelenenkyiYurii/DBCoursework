package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.Instructor;
import com.example.courseworkdb.entities.TheoreticalLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TheoreticalLessonRepository extends JpaRepository<TheoreticalLesson, Integer> {

    @Override
    <S extends TheoreticalLesson> S save(S entity);

    @Override
    Optional<TheoreticalLesson> findById(Integer integer);

    @Override
    List<TheoreticalLesson> findAll();

    @Override
    void deleteById(Integer integer);

    List<TheoreticalLesson> findByGroup_Enrollments_Student_Id_OrderByStartDate(Integer id);

    Optional<TheoreticalLesson> findByInstructorAndStartDate(Instructor instructor, Instant startDate);
}