package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    @Override
    void deleteById(Integer integer);

    @Override
    <S extends Enrollment> S save(S entity);

    @Override
    List<Enrollment> findAll();

    @Override
    Optional<Enrollment> findById(Integer integer);

    @Transactional
    @Modifying
    @Query("update Enrollment e set e.enrollmentDate = ?1, e.student = ?2, e.course = ?3, e.group = ?4 where e.id = ?5")
    int updateEnrollmentDateAndStudentAndCourseAndGroupById(Instant enrollmentDate, Student student, Cours course, StudentGroup group, Integer id);

    @Transactional
    @Modifying
    @Query("update Enrollment e set e.status = ?1 where e.id = ?2")
    int updateStatusById(EnrollmentStatus status, Integer id);

    List<Enrollment> findByStatus(EnrollmentStatus status);
}