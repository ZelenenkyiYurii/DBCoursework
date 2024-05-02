package com.example.courseworkdb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "practical_lessons", indexes = {
        @Index(name = "unique_time_instructor", columnList = "start_date, instructor_id", unique = true),
        @Index(name = "unique_time_car", columnList = "start_date, car_id", unique = true)
})
public class PracticalLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "practical_lesson_id", nullable = false)
    private Integer id;

    @Column(name = "start_date")
    private Instant startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "duration_hours")
    private Integer durationHours;

}