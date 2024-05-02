package com.example.courseworkdb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Integer id;

    @Column(name = "course_name", length = 100)
    private String courseName;

    @Column(name = "duration_hours")
    private Integer durationHours;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments = new LinkedHashSet<>();
    @OneToMany(mappedBy = "course")
    private Set<StudentGroup> studentGroups = new LinkedHashSet<>();

    @Column(name = "course_type", columnDefinition = "course_type_enum(0, 0)")
    private String courseType;


    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new LinkedHashSet<>();

    @Override
    public String toString() {
        return courseName;
    }
}