package com.example.courseworkdb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "students", indexes = {
        @Index(name = "unique_email_students", columnList = "email", unique = true),
        @Index(name = "idx_students_email", columnList = "email")
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Integer id;
    @NotEmpty
    @Column(name = "first_name", length = 50)
    private String firstName;
    @NotEmpty
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @NotEmpty
    @Email
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "student_password", length = 100)
    private String studentPassword;
    @NotEmpty
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "student_course_exams",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Cours> courses = new LinkedHashSet<>();

}