package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.TeacherAssignment;

public interface TeacherAssignmentRepo extends JpaRepository<TeacherAssignment, Long> {

    List<TeacherAssignment> findAllByOrderByAssignedAtDesc();
}