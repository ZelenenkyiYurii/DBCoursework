package com.example.courseworkdb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Column(name = "category_name", length = 100)
    private String categoryName;

    @Column(name = "min_age")
    private Integer minAge;

    @OneToMany(mappedBy = "category")
    private Set<Car> cars = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<Cours> courses = new LinkedHashSet<>();

}