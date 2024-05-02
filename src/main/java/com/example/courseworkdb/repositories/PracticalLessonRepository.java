package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.Car;
import com.example.courseworkdb.entities.Enrollment;
import com.example.courseworkdb.entities.Instructor;
import com.example.courseworkdb.entities.PracticalLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface PracticalLessonRepository extends JpaRepository<PracticalLesson, Integer> {

    @Override
    <S extends PracticalLesson> S save(S entity);

    @Override
    Optional<PracticalLesson> findById(Integer integer);

    @Override
    List<PracticalLesson> findAll();

    @Override
    void deleteById(Integer integer);

    List<PracticalLesson> findByEnrollment_Student_Id(Integer id);

    Optional<PracticalLesson> findByInstructorAndStartDate(Instructor instructor, Instant startDate);

    Optional<PracticalLesson> findByStartDateAndCar(Instant startDate, Car car);

    List<PracticalLesson> findByEnrollment(Enrollment enrollment);

    long countByEnrollment(Enrollment enrollment);



}