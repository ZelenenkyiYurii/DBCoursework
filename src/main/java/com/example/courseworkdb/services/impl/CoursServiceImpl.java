package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Cours;
import com.example.courseworkdb.entities.Student;
import com.example.courseworkdb.entities.StudentCourseExam;
import com.example.courseworkdb.repositories.CoursRepository;
import com.example.courseworkdb.repositories.StudentCourseExamRepository;
import com.example.courseworkdb.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CoursServiceImpl implements CoursService {
    final
    CoursRepository coursRepository;
    final
    StudentCourseExamRepository studentCourseExamRepository;

    public CoursServiceImpl(CoursRepository coursRepository, StudentCourseExamRepository studentCourseExamRepository) {
        this.coursRepository = coursRepository;
        this.studentCourseExamRepository = studentCourseExamRepository;
    }

    @Override
    public List<Cours> getAll() {
        return coursRepository.findAll();
    }

    @Override
    public Optional<Cours> getById(int id) {
        return coursRepository.findById(id);
    }

    @Override
    public void create(Cours obj) {
        coursRepository.save(obj);
    }

    @Override
    public void deleteById(int id) {
        coursRepository.deleteById(id);
    }

    @Override
    public void update(int id, Cours obj) {

    }
    public List<Cours> getCourseForStudent(int student){
        List<Cours> list=coursRepository.findAll();
        List<StudentCourseExam> studentCourseExams=studentCourseExamRepository.findByStudent_Id(student);
        for (StudentCourseExam courseExam:studentCourseExams
             ) {
            list.removeIf(x->x.getCourseName().equals(courseExam.getCourse().getCourseName()));
        }
        return list;
    }
}
