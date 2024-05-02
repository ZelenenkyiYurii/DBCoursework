package com.example.courseworkdb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Cours course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private StudentGroup group;

    @Column(name = "enrollment_date")
    private Instant enrollmentDate;

    @OneToMany(mappedBy = "enrollment")
    private Set<Exam> exams = new LinkedHashSet<>();

    @OneToMany(mappedBy = "enrollment")
    private Set<Payment> payments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "enrollment")
    private Set<PracticalLesson> practicalLessons = new LinkedHashSet<>();

    @OneToMany(mappedBy = "enrollment")
    private Set<Review> reviews = new LinkedHashSet<>();

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

//    @Column(name = "status", columnDefinition = "enrollment_status_enum(0, 0)")
//    private String status;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "status", nullable = false)
//    private EnrollmentStatus status;

}