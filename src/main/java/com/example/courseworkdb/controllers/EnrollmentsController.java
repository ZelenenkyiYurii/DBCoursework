package com.example.courseworkdb.controllers;


import com.example.courseworkdb.entities.Enrollment;

import com.example.courseworkdb.entities.EnrollmentStatus;
import com.example.courseworkdb.entities.Payment;
import com.example.courseworkdb.services.CoursService;
import com.example.courseworkdb.services.impl.CoursServiceImpl;
import com.example.courseworkdb.services.impl.EnrollmentServiceImpl;
import com.example.courseworkdb.services.impl.PaymentServiceImpl;
import com.example.courseworkdb.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentsController {
    final
    EnrollmentServiceImpl enrollmentService;
    final
    StudentServiceImpl studentService;
    final
    CoursServiceImpl coursService;
    final
    PaymentServiceImpl paymentService;

    public EnrollmentsController(EnrollmentServiceImpl enrollmentService, StudentServiceImpl studentService, CoursServiceImpl coursService, PaymentServiceImpl paymentService) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.coursService = coursService;
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        Optional<Enrollment> enrollment=enrollmentService.getById(id);

        if(enrollment.isPresent()){
            model.addAttribute("enrollment",enrollment.get());
        }else {
            return "redirect:/admin_page";
        }

        return "views/admin_page/edit/enrollment_edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("enrollment") Enrollment entity, BindingResult bindingResult, @PathVariable("id") int id)
    {
        if(bindingResult.hasErrors())
            return "views/admin_page/edit/enrollment_edit";
        enrollmentService.update(id,entity);
        return "redirect:/admin_page";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        enrollmentService.deleteById(id);
        return "redirect:/admin_page";
    }
    @GetMapping("/new/{id}")
    public String newPerson(@PathVariable("id") int id,Model model)
    {
        Enrollment enrollment=new Enrollment();
        enrollment.setStatus("in_progress");
        enrollment.setEnrollmentDate(Instant.now());
        System.out.println(id);
        enrollment.setStudent(studentService.getStudentsById(id).get());
        System.out.println(studentService.getStudentsById(id).isPresent());
        model.addAttribute("courses",coursService.getCourseForStudent(id));
        model.addAttribute("student",studentService.getStudentsById(id).get());
        model.addAttribute("enrollment",enrollment);
        return "views/admin_page/new/enrollment_new";
    }
    @PostMapping("/new")
    public String create(@ModelAttribute("enrollment")  Enrollment enrollment, BindingResult bindingResult,Model model)
    {
//        if(bindingResult.hasErrors())
//            return "views/admin_page/new/enrollment_new";

        enrollmentService.create(enrollment);
        int id=enrollment.getId();

        return ("redirect:/enrollments/pay/"+id);
    }

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable("id")int id,Model model){
        model.addAttribute("enrollment",enrollmentService.getById(id).get());
        model.addAttribute("price",enrollmentService.getById(id).get().getCourse().getPrice());
        Payment payment=new Payment();
        model.addAttribute("payment",payment);
        return "views/payment";
    }

    @PostMapping("/pays/{id}")
    public String payCourse(@PathVariable("id")int id){

        Payment payment=new Payment();
        payment.setEnrollment(enrollmentService.getById(id).get());
        payment.setAmount(enrollmentService.getById(id).get().getCourse().getPrice());
        payment.setPaymentDate(Instant.now());
        paymentService.create(payment);
        return "redirect:/student_page/"+enrollmentService.getById(id).get().getStudent().getId();
    }

//    @GetMapping("/up")
//    public String generate()
//    {
//        enrollmentService.updateStatus();
//        return "views/adminMainPage";
//    }
}
