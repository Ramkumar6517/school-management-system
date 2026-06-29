// package com.school.repository;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.school.entity.Student;

// public interface StudentRepository extends JpaRepository<Student, Long> {

//     List<Student> findByStatus(String status);

//     Optional<Student> findByApplicationId(String applicationId);

//     Optional<Student> findByStudentId(String studentId);


//        List<Student> findByStudentNameContainingIgnoreCaseOrClassNameContainingIgnoreCaseOrApplicationIdContainingIgnoreCaseOrEmailContainingIgnoreCase(
            
//             String name,
//             String className,
//             String applicationId,
//             String email
//     );
//       List<Student> findByClassName(String className);

// }

package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // 🔹 Status filter
    List<Student> findByStatus(String status);

    // 🔹 Unique lookups
    Optional<Student> findByApplicationId(String applicationId);
    Optional<Student> findByStudentId(String studentId);

    // 🔹 Search (admin panel search)
    List<Student> findByStudentNameContainingIgnoreCaseOrClassNameContainingIgnoreCaseOrApplicationIdContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name,
            String className,
            String applicationId,
            String email
    );

    // 🔹 Class-wise students
    List<Student> findByClassName(String className);
}