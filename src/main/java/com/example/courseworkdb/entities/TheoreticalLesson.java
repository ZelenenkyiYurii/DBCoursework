package com.example.courseworkdb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "theoretical_lessons", indexes = {
        @Index(name = "unique_time_instructor_theoretical", columnList = "start_date, instructor_id", unique = true)
})
public class TheoreticalLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theoretical_lesson_id", nullable = false)
    private Integer id;

    @Column(name = "start_date")
    private Instant startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private StudentGroup group;

    @Column(name = "duration_hours")
    private Integer durationHours;

}