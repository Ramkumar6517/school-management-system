package com.school.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.Subject;


public interface SubjectRepository 
        extends JpaRepository<Subject, Long>{

}