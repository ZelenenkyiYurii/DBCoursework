package com.example.courseworkdb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "instructors", indexes = {
        @Index(name = "unique_email_instructors", columnList = "email", unique = true)
})
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 100)
    @Column(name = "instructor_password", length = 100)
    private String instructorPassword;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @OneToMany(mappedBy = "instructor")
    private Set<PracticalLesson> practicalLessons = new LinkedHashSet<>();

    @OneToMany(mappedBy = "instructor")
    private Set<TheoreticalLesson> theoreticalLessons = new LinkedHashSet<>();

}