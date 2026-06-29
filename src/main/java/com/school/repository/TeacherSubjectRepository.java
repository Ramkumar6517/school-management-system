package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.TeacherSubject;

public interface TeacherSubjectRepository 
extends JpaRepository<TeacherSubject, Long>{


    List<TeacherSubject> findByTeacherId(Long teacherId);


}