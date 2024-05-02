package com.example.courseworkdb.controllers;

import com.example.courseworkdb.entities.Enrollment;
import com.example.courseworkdb.entities.PracticalLesson;
import com.example.courseworkdb.entities.Student;
import com.example.courseworkdb.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Controller
@RequestMapping("/student_page")
public class StudentPageController {
    final
    TheoreticalLessonServiceImpl theoreticalLessonService;
    final
    PracticalLessonServiceImpl practicalLessonService;
    final
    StudentServiceImpl studentService;
    final
    InstructorServiceImpl instructorService;
    final
    CarServiceImpl carService;
    final
    EnrollmentServiceImpl enrollmentService;

    public StudentPageController(TheoreticalLessonServiceImpl theoreticalLessonService, PracticalLessonServiceImpl practicalLessonService, StudentServiceImpl studentService, InstructorServiceImpl instructorService, CarServiceImpl carService, EnrollmentServiceImpl enrollmentService) {
        this.theoreticalLessonService = theoreticalLessonService;
        this.practicalLessonService = practicalLessonService;
        this.studentService = studentService;
        this.instructorService = instructorService;
        this.carService = carService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/{id}")
    public String homePage(Model model,@PathVariable("id") int id){
        model.addAttribute("practicals",practicalLessonService.getAllByStudentId(id));
        model.addAttribute("theoreticals",theoreticalLessonService.getAllByStudentId(id));
        model.addAttribute("student",studentService.getStudentsById(id));
        return "views/studentMainPage";
    }
    @GetMapping("/new_lesson")
    public String newLesson(
                            @RequestParam(value = "enrollment") int enrollment,
                            @RequestParam(value = "date") LocalDateTime date,
                            Model model){
        PracticalLesson practicalLesson=new PracticalLesson();
        practicalLesson.setStartDate(date.toInstant(ZoneOffset.UTC));
        practicalLesson.setDurationHours(1);
        practicalLesson.setEnrollment(enrollmentService.getById(enrollment).get());
        System.out.println(enrollment);
        System.out.println(date);
        model.addAttribute("cars",carService.getAllFreeCar(date.toInstant(ZoneOffset.UTC)));
        model.addAttribute("instructors",instructorService.getAllFreeInstructors(date.toInstant(ZoneOffset.UTC)));
        model.addAttribute("enrollment",enrollmentService.getById(enrollment));
        model.addAttribute("date",date);
        model.addAttribute("duration",1);
        model.addAttribute("lesson",practicalLesson);
        return "views/new_lesson";
    }

    @PostMapping("/new_lesson")
    public String createNewLesson(@ModelAttribute("lesson")PracticalLesson practicalLesson){
//        model.addAttribute("cars",carService.getAllFreeCar(date));
//        model.addAttribute("instructors",instructorService.getAllFreeInstructors(date));
//        model.addAttribute("enrollment",enrollment);
//        model.addAttribute("date",date);
//        model.addAttribute("duration",1);
        if(practicalLesson.getEnrollment()!=null) {
            practicalLessonService.create(practicalLesson);
        }
        return ("redirect:/student_page/"+practicalLesson.getEnrollment().getStudent().getId());
    }
}
