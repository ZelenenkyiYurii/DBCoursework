package com.example.courseworkdb.repositories;

import com.example.courseworkdb.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    public Optional<Instructor> findFirstByEmailAndInstructorPassword(String email, String instructorPassword);

    @Query("SELECT i FROM Instructor i WHERE i.email = :email AND i.instructorPassword = :password")
    Instructor findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Override
    void deleteById(Integer integer);

    @Override
    Optional<Instructor> findById(Integer integer);

    @Override
    List<Instructor> findAll();

    @Transactional
    @Modifying
    @Query("update Instructor i set i.firstName = ?1, i.lastName = ?2, i.email = ?3, i.phoneNumber = ?4 where i.id = ?5")
    int updateFirstNameAndLastNameAndEmailAndPhoneNumberById(String firstName, String lastName, String email, String phoneNumber, Integer id);

    List<Instructor> findByPracticalLessons_StartDateNot(Instant startDate);

    List<Instructor> findByPracticalLessons_StartDateNotAndTheoreticalLessons_StartDateNot(Instant startDate, Instant startDate1);
}