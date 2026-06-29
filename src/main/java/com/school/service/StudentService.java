package com.school.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.school.entity.Student;
import com.school.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    // =========================
    // STUDENT REGISTRATION
    // =========================
    public Student saveStudent(Student student,
                                MultipartFile photo,
                                MultipartFile marksheet,
                                MultipartFile aadhaarCard) {

        try {

            student.setStatus("PENDING");
            student.setPassword(student.getDob());

            Student saved = repo.save(student);

            // APPLICATION ID
            String appId = "APP2026" + String.format("%05d", saved.getId());
            saved.setApplicationId(appId);

            // UPLOAD DIR
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            // PHOTO
            if (photo != null && !photo.isEmpty()) {
                String name = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
                photo.transferTo(new File(uploadDir + name));
                saved.setPhotoPath(name);
            }

            // MARKSHEET
            if (marksheet != null && !marksheet.isEmpty()) {
                String name = System.currentTimeMillis() + "_" + marksheet.getOriginalFilename();
                marksheet.transferTo(new File(uploadDir + name));
                saved.setMarksheetPath(name);
            }

            // AADHAAR
            if (aadhaarCard != null && !aadhaarCard.isEmpty()) {
                String name = System.currentTimeMillis() + "_" + aadhaarCard.getOriginalFilename();
                aadhaarCard.transferTo(new File(uploadDir + name));
                saved.setAadhaarCardPath(name);
            }

            return repo.save(saved);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // =========================
    // LOGIN SYSTEM (LOCK FEATURE)
    // =========================
    public String login(String studentId, String password) {

        Optional<Student> opt = repo.findByStudentId(studentId);

        if (opt.isEmpty()) return "Student not found";

        Student student = opt.get();

        if (!student.isEnabled()) return "Account disabled by admin";

        if (student.isLocked()) return "Account locked. Contact admin";

        if (student.getPassword().equals(password)) {

            student.setFailedAttempts(0);
            repo.save(student);

            return "Login successful";
        }

        int attempts = student.getFailedAttempts() + 1;
        student.setFailedAttempts(attempts);

        if (attempts >= 3) {
            student.setLocked(true);
            repo.save(student);
            return "Account locked after 3 failed attempts";
        }

        repo.save(student);

        return "Wrong password. Attempt " + attempts + "/3";
    }
    // =========================
// ADMIN: LOCK STUDENT
// =========================

public String lockStudent(String studentId) {


    Optional<Student> opt = repo.findByStudentId(studentId);


    if(opt.isEmpty()) {

        return "Student not found";

    }


    Student student = opt.get();


    student.setLocked(true);


    repo.save(student);


    return "Student account locked successfully";

}

    // =========================
    // ADMIN: UNLOCK STUDENT
    // =========================
    public String unlockStudent(String studentId) {

        Optional<Student> opt = repo.findByStudentId(studentId);

        if (opt.isEmpty()) return "Student not found";

        Student student = opt.get();

        student.setLocked(false);
        student.setFailedAttempts(0);

        repo.save(student);

        return "Student unlocked successfully";
    }

    

    // =========================
    // ADMIN: ENABLE / DISABLE
    // =========================
    public String toggleEnable(String studentId, boolean status) {

        Optional<Student> opt = repo.findByStudentId(studentId);

        if (opt.isEmpty()) return "Student not found";

        Student student = opt.get();

        student.setEnabled(status);

        repo.save(student);

        return status ? "Student enabled" : "Student disabled";
    }

    // =========================
    // BASIC CRUD
    // =========================
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public List<Student> getPendingStudents() {
        return repo.findByStatus("PENDING");
    }

    public List<Student> getApprovedStudents() {
        return repo.findByStatus("APPROVED");
    }

    public Student getStudent(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void approveStudent(Long id) {
        Student student = repo.findById(id).orElse(null);
        if (student != null) {
            student.setStatus("APPROVED");
            repo.save(student);
        }
    }

    public void rejectStudent(Long id) {
        Student student = repo.findById(id).orElse(null);
        if (student != null) {
            student.setStatus("REJECTED");
            repo.save(student);
        }
    }

    public void generateStudentId(Long id) {
        Student student = repo.findById(id).orElse(null);
        if (student != null) {
            student.setStudentId("STU" + System.currentTimeMillis());
            repo.save(student);
        }
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }

    public Student getStudentById(Long id) {
        return repo.findById(id).orElse(null);
    }

     public Student findByStudentId(String studentId) {
        return repo.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}