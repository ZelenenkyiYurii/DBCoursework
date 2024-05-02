package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Car;
import com.example.courseworkdb.entities.Instructor;
import com.example.courseworkdb.repositories.InstructorRepository;
import com.example.courseworkdb.services.InstructorService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class InstructorServiceImpl implements InstructorService {
    final
    InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAll() {
        return instructorRepository.findAll();

    }

    @Override
    public Optional<Instructor> getById(int id) {
        return instructorRepository.findById(id);
    }

    @Override
    public void create(Instructor obj) {
        instructorRepository.save(obj);
    }

    @Override
    public void deleteById(int id) {
        instructorRepository.deleteById(id);
    }

    @Override
    public void update(int id,Instructor obj) {
       Optional<Instructor> instructorOptional= instructorRepository.findById(id);
       if(instructorOptional.isPresent()){
           Instructor instructor=instructorOptional.get();
           instructorRepository.save(obj);
       }
    }
    public Instructor getRandom(){
        List<Instructor> allCars = instructorRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allCars.size());
        return allCars.get(randomIndex);
    }
    public List<Instructor> getAllFreeInstructors(Instant dateTime){
        return instructorRepository
                .findByPracticalLessons_StartDateNotAndTheoreticalLessons_StartDateNot
                        (dateTime, dateTime);
    }
}
