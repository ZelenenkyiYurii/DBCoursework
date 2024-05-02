package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Override
    <S extends Payment> S save(S entity);

    @Override
    List<Payment> findAll();

    @Override
    Optional<Payment> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}