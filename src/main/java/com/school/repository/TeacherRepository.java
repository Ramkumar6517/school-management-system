// package com.school.repository;


// import org.springframework.data.jpa.repository.JpaRepository;

// import com.school.entity.Teacher;


// public interface TeacherRepository 
// extends JpaRepository<Teacher,Long>{


// Teacher findByTeacherId(String teacherId);


// }

package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    // OPTIONAL (only if you really have teacherId field in entity)
    Teacher findByTeacherId(String teacherId);
}

