package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.StudentCourseExam;
import com.example.courseworkdb.entities.StudentCourseExamId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCourseExamRepository extends JpaRepository<StudentCourseExam, StudentCourseExamId> {
    @Override
    <S extends StudentCourseExam> S save(S entity);

    List<StudentCourseExam> findByStudent_Id(Integer id);
}