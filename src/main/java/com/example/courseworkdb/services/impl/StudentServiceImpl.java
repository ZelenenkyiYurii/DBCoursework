package com.example.courseworkdb.services.impl;



import com.example.courseworkdb.entities.Student;
import com.example.courseworkdb.repositories.StudentRepository;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl {

    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    public StudentServiceImpl(ModelMapper modelMapper, StudentRepository studentRepository) {
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Optional<Student> getStudentsById(int id){
        return studentRepository.findById(id);
    }
    public void update(int id,Student student){
        //studentRepository.save(student);
        studentRepository.updateFirstNameAndLastNameAndDateOfBirthAndEmailAndPhoneNumberById(
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getId()
        );
    }
    public void deleteById(int id){
        studentRepository.deleteById(id);
    }
    public void create(Student student){
//        studentRepository.callAddStudentFunction(student.getFirstName(),
//                student.getLastName(),
//                student.getDateOfBirth(),
//                student.getPhoneNumber());
        studentRepository.save(student);
    }
}
