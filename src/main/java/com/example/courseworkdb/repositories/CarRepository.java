package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    @Override
    void deleteById(Integer integer);

    @Override
    <S extends Car> S save(S entity);

    @Override
    List<Car> findAll();

    @Override
    Optional<Car> findById(Integer integer);

    List<Car> findByPracticalLessons_StartDateNot(Instant startDate);


}