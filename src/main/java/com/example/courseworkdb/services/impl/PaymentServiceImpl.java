package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Payment;
import com.example.courseworkdb.repositories.PaymentRepository;
import com.example.courseworkdb.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PaymentServiceImpl implements PaymentService {
    final
    PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAll() {
        return null;
    }

    @Override
    public Optional<Payment> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void create(Payment obj) {
        paymentRepository.save(obj);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(int id, Payment obj) {

    }
}
