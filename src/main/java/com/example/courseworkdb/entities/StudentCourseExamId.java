package com.example.courseworkdb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StudentCourseExamId implements Serializable {
    private static final long serialVersionUID = 1239059172657917682L;
    @NotNull
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @NotNull
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentCourseExamId entity = (StudentCourseExamId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.courseId, entity.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

}