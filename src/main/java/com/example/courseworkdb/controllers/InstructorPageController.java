package com.example.courseworkdb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor_page")
public class InstructorPageController {
    @GetMapping("")
    public String homePage(){
        return "views/instructorMainPage";
    }
}
