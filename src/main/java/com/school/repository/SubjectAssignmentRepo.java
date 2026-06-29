package com.school.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.SubjectAssignment;


public interface SubjectAssignmentRepo 
extends JpaRepository<SubjectAssignment, Long>{


    List<SubjectAssignment> findByTeacherName(String teacherName);


}