package com.example.courseworkdb.services;


import com.example.courseworkdb.repositories.InstructorRepository;
import com.example.courseworkdb.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface LoginService {
    public String login(String email, String password);
}
