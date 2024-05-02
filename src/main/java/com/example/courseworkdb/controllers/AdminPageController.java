package com.example.courseworkdb.controllers;


import com.example.courseworkdb.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin_page")
public class AdminPageController {
    final StudentServiceImpl studentService;
    final InstructorServiceImpl instructorService;
    final EnrollmentServiceImpl enrollmentService;
    final CoursServiceImpl coursService;
    final TheoreticalLessonServiceImpl theoreticalLessonService;
    final PracticalLessonServiceImpl practicalLessonService;


    public AdminPageController(StudentServiceImpl studentService, InstructorServiceImpl instructorService, EnrollmentServiceImpl enrollmentService, CoursServiceImpl coursService, TheoreticalLessonServiceImpl theoreticalLessonService, PracticalLessonServiceImpl practicalLessonService) {
        this.studentService = studentService;
        this.instructorService = instructorService;
        this.enrollmentService = enrollmentService;
        this.coursService = coursService;
        this.theoreticalLessonService = theoreticalLessonService;
        this.practicalLessonService = practicalLessonService;
    }

    @GetMapping("")
    public String homePage(Model model){
        model.addAttribute(
                "students",
                studentService.getAllStudents());
        model.addAttribute("instructors",
                instructorService.getAll());
        model.addAttribute("enrollments",
                enrollmentService.getAll());

        model.addAttribute("courses",
                coursService.getAll());

        model.addAttribute("theoretical_lessons",
                theoreticalLessonService.getAll());
        model.addAttribute("practical_lessons",
                practicalLessonService.getAll());
        return "views/adminMainPage";
    }
//    @GetMapping("/generatePLessons")
//    public String generateLessons(Model model){
//        practicalLessonService.generateAllLessons();
//        model.addAttribute(
//                "students",
//                studentService.getAllStudents());
//        model.addAttribute("instructors",
//                instructorService.getAll());
//        model.addAttribute("enrollments",
//                enrollmentService.getAll());
//        System.out.println("do course");
//        model.addAttribute("courses",
//                coursService.getAll());
//        System.out.println("afte");
//        model.addAttribute("theoretical_lessons",
//                theoreticalLessonService.getAll());
//        model.addAttribute("practical_lessons",
//                practicalLessonService.getAll());
//        return "views/adminMainPage";
//    }

}
