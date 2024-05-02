package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Car;
import com.example.courseworkdb.repositories.CarRepository;
import com.example.courseworkdb.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CarServiceImpl implements CarService {
    final
    CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    @Override
    public Optional<Car> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void create(Car obj) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(int id, Car obj) {

    }
    public Car getRandom(){
            List<Car> allCars = carRepository.findAll();
            Random random = new Random();
            int randomIndex = random.nextInt(allCars.size());
            return allCars.get(randomIndex);
    }

    public List<Car> getAllFreeCar(Instant date){
        return carRepository.findByPracticalLessons_StartDateNot(date);
    }
}
