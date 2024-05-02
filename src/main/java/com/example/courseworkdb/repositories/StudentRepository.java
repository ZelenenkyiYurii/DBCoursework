package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.Student;
import jakarta.persistence.NamedStoredProcedureQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT add_student(:firstName, :lastName, :phone,:dateOfBirth)", nativeQuery = true)
    Integer callAddStudentFunction(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName,
                                   @Param("dateOfBirth") LocalDate dateOfBirth,
                                   @Param("phone") String phone);

    Optional<Student> findByEmailAndStudentPassword(String email, String studentPassword);

    @Override
    List<Student> findAll();

    @Override
    Optional<Student> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    <S extends Student> S save(S entity);

    @Transactional
    @Modifying
    @Query("""
            update Student s set s.firstName = ?1, s.lastName = ?2, s.dateOfBirth = ?3, s.email = ?4, s.phoneNumber = ?5
            where s.id = ?6""")
    int updateFirstNameAndLastNameAndDateOfBirthAndEmailAndPhoneNumberById(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, Integer id);



}