package com.example.courseworkdb.controllers;


import com.example.courseworkdb.entities.Instructor;
import com.example.courseworkdb.services.impl.InstructorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@Controller
@RequestMapping("/instructors")
public class InstructorsController {
    final
    InstructorServiceImpl instructorService;



    public InstructorsController(InstructorServiceImpl instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        Optional<Instructor> instructor=instructorService.getById(id);

        if(instructor.isPresent()){
            model.addAttribute("instructor",instructor.get());
        }else {
            return "redirect:/admin_page";
        }

        return "views/admin_page/edit/instructor_edit";
    }



    @PatchMapping("/{id}")
    public String update(@ModelAttribute("instructor") Instructor entity, BindingResult bindingResult, @PathVariable("id") int id)
    {
        if(bindingResult.hasErrors())
            return "views/admin_page/edit/instructor_edit";
        instructorService.update(id,entity);
        return "redirect:/admin_page";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        instructorService.deleteById(id);
        return "redirect:/admin_page";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("instructor") Instructor entity)
    {
        return "views/admin_page/new/instructor_new";
    }
    @PostMapping()
    public String create(@ModelAttribute("instructor")  Instructor instructor, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "views/admin_page/new/instructor_new";

        instructorService.create(instructor);
        return "redirect:/admin_page";
    }
}
