package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Instructor;
import com.example.courseworkdb.entities.Student;
import com.example.courseworkdb.repositories.InstructorRepository;
import com.example.courseworkdb.repositories.StudentRepository;
import com.example.courseworkdb.services.LoginService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    private final InstructorRepository instructor_repository;
    private final StudentRepository student_repository;

    public LoginServiceImpl(InstructorRepository instructor_repository, StudentRepository student_repository) {
        this.instructor_repository = instructor_repository;
        this.student_repository = student_repository;
    }

    public String login(String email, String password){
        if(email.equals("admin@gmail.com") && password.equals("admin")){
            return "redirect:/admin_page";
        } else {
        Optional<Instructor> instructor = instructor_repository.findFirstByEmailAndInstructorPassword(email, password);
        if (instructor.isPresent()) {
            int id=instructor.get().getId();
            return ("redirect:/instructor_page/"+id);
        } else {
            Optional<Student> student = student_repository.findByEmailAndStudentPassword(email, password);
            if (student.isPresent()) {
                int id=student.get().getId();
                return ("redirect:/student_page/"+id);
            }
        }
        }
        return "redirect:/";

    }
}
