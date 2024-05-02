package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Enrollment;
import com.example.courseworkdb.entities.TheoreticalLesson;
import com.example.courseworkdb.repositories.TheoreticalLessonRepository;
import com.example.courseworkdb.services.TheoreticalLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TheoreticalLessonServiceImpl implements TheoreticalLessonService {
    final
    TheoreticalLessonRepository theoreticalLessonRepository;

    public TheoreticalLessonServiceImpl(TheoreticalLessonRepository theoreticalLessonRepository) {
        this.theoreticalLessonRepository = theoreticalLessonRepository;
    }

    @Override
    public List<TheoreticalLesson> getAll() {
        return null;
    }

    @Override
    public Optional<TheoreticalLesson> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void create(TheoreticalLesson obj) {
        theoreticalLessonRepository.save(obj);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(int id, TheoreticalLesson obj) {

    }
    public List<TheoreticalLesson> getAllByStudentId(int id){
        return theoreticalLessonRepository.findByGroup_Enrollments_Student_Id_OrderByStartDate(id);
    }

}
