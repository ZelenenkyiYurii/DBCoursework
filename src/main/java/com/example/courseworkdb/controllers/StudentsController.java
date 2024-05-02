package com.example.courseworkdb.controllers;



import com.example.courseworkdb.entities.Cours;
import com.example.courseworkdb.entities.Student;
import com.example.courseworkdb.services.CoursService;
import com.example.courseworkdb.services.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentsController {
    final StudentServiceImpl studentService;
    final
    CoursService coursService;

    public StudentsController(StudentServiceImpl studentService, CoursService coursService) {
        this.studentService = studentService;
        this.coursService = coursService;
    }

    @GetMapping("")
    public String homePage(){
        return "views/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        Optional<Student> student=studentService.getStudentsById(id);

        if(student.isPresent()){
            model.addAttribute("student",student.get());
        }else {
            return "redirect:/admin_page";
        }

        return "views/admin_page/edit/student_edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult, @PathVariable("id") int id)
    {
        if(bindingResult.hasErrors())
            return "views/admin_page/edit/student_edit";
        studentService.update(id,student);
        return "redirect:/admin_page";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        studentService.deleteById(id);
        return "redirect:/admin_page";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("student") Student student,Model model)
    {
        model.addAttribute("courses",coursService.getAll());
        return "views/admin_page/new/student_new";
    }
    @PostMapping()
    public String create(@ModelAttribute("student") @Valid  Student student, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "views/admin_page/new/student_new";
        studentService.create(student);
        return "redirect:/admin_page";
    }
}
