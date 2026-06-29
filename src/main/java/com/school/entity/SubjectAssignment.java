package com.school.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


    @Entity
public class SubjectAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String teacherName;

    private String subjectName;

    private String className;

    private String assignedBy;

    private LocalDateTime assignedAt;

    @Column(nullable = false)
private boolean editEnabled = true;



    // getter setter

    public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}


public String getTeacherName() {
    return teacherName;
}

public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
}


public String getSubjectName() {
    return subjectName;
}

public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
}


public String getClassName() {
    return className;
}

public void setClassName(String className) {
    this.className = className;
}


public String getAssignedBy() {
    return assignedBy;
}

public void setAssignedBy(String assignedBy) {
    this.assignedBy = assignedBy;
}


public LocalDateTime getAssignedAt() {
    return assignedAt;
}

public void setAssignedAt(LocalDateTime assignedAt) {
    this.assignedAt = assignedAt;
}
public boolean isEditEnabled() {
    return editEnabled;
}

public void setEditEnabled(boolean editEnabled) {
    this.editEnabled = editEnabled;
}
}

