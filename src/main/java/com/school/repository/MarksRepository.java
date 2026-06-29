package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.Marks;
import com.school.entity.Student;


public interface MarksRepository extends JpaRepository<Marks, Long> {


    List<Marks> findByStudent(Student student);


    Optional<Marks> findByStudentAndSubject(
            Student student,
            String subject
    );


}