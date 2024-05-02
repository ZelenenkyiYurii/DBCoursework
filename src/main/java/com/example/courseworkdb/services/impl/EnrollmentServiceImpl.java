package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Enrollment;
import com.example.courseworkdb.entities.EnrollmentStatus;
import com.example.courseworkdb.entities.Payment;
import com.example.courseworkdb.entities.PracticalLesson;
import com.example.courseworkdb.repositories.EnrollmentRepository;
import com.example.courseworkdb.repositories.PaymentRepository;
import com.example.courseworkdb.repositories.PracticalLessonRepository;
import com.example.courseworkdb.repositories.TheoreticalLessonRepository;
import com.example.courseworkdb.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    final
    EnrollmentRepository enrollmentRepository;
    final
    PaymentRepository paymentRepository;
    final
    PracticalLessonRepository practicalLessonRepository;
    final
    TheoreticalLessonRepository theoreticalLessonRepository;


    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, PaymentRepository paymentRepository, PracticalLessonRepository practicalLessonRepository, TheoreticalLessonRepository theoreticalLessonRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.paymentRepository = paymentRepository;
        this.practicalLessonRepository = practicalLessonRepository;
        this.theoreticalLessonRepository = theoreticalLessonRepository;
    }

    @Override
    public List<Enrollment> getAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Optional<Enrollment> getById(int id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public void create(Enrollment obj) {
        enrollmentRepository.save(obj);
    }

    @Override
    public void deleteById(int id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public void update(int id, Enrollment obj) {
        enrollmentRepository.updateEnrollmentDateAndStudentAndCourseAndGroupById(
                obj.getEnrollmentDate(),
                obj.getStudent(),
                obj.getCourse(),
                obj.getGroup(),
                id);
    }
    public void generatePay(){
        List<Enrollment> enrollmentList=enrollmentRepository.findAll();
        for (Enrollment enrollment:enrollmentList
             ) {
            Payment payment=new Payment();
            payment.setEnrollment(enrollment);
            payment.setPaymentDate(enrollment.getEnrollmentDate().plusSeconds(6000));
            payment.setAmount(enrollment.getCourse().getPrice());
            paymentRepository.save(payment);
        }
    }
    public void updateStatus(){
        List<Enrollment> enrollmentList=enrollmentRepository.findAll();
        for (Enrollment enrollment:enrollmentList
        ) {
            if(enrollment.getCourse().getCourseType().equals("practical")){
                //List<PracticalLesson> practicalLessons=practicalLessonRepository.findByEnrollment(enrollment);
                long count=practicalLessonRepository.countByEnrollment(enrollment);
                if(count>=enrollment.getCourse().getDurationHours()){
                    enrollmentRepository.updateStatusById(EnrollmentStatus.completed, enrollment.getId());
                }
            }else {
                if(enrollment.getGroup().getStatus().equals("recruiting_students")){
                    enrollmentRepository.updateStatusById(EnrollmentStatus.pending, enrollment.getId());
                }else if(enrollment.getGroup().getStatus().equals("in_progress")){
                    enrollmentRepository.updateStatusById(EnrollmentStatus.in_progress,enrollment.getId());
                }else {
                    enrollmentRepository.updateStatusById(EnrollmentStatus.completed,enrollment.getId());
                }
            }
        }
    }



}
