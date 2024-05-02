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
@Table(name = "student_groups", indexes = {
        @Index(name = "idx_student_groups_course_id", columnList = "course_id")
})
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Integer id;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Cours course;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "start_date")
    private Instant startDate;

    @OneToMany(mappedBy = "group")
    private Set<Enrollment> enrollments = new LinkedHashSet<>();
    @OneToMany(mappedBy = "group")
    private Set<TheoreticalLesson> theoreticalLessons = new LinkedHashSet<>();

    @Column(name = "status", columnDefinition = "group_status_enum(0, 0)")
    private String status;

}