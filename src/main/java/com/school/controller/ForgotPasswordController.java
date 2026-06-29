package com.school.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.entity.Student;
import com.school.repository.StudentRepository;
import com.school.service.EmailService;

@RestController
public class ForgotPasswordController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailService emailService;

    // SEND OTP

    @PostMapping("/forgot-password")

    public String forgotPassword(
            @RequestBody Map<String, String> data) {

        String studentId = data.get("studentId");

        Optional<Student> optional = studentRepository.findByStudentId(studentId);

        if (optional.isEmpty()) {

            return "Student not found";

        }

        Student student = optional.get();

        String otp = String.valueOf(
                (int) (Math.random() * 900000) + 100000);

        student.setResetOtp(otp);

        student.setOtpExpiry(
                LocalDateTime.now()
                        .plusMinutes(5));

        studentRepository.save(student);

        emailService.sendOtp(
                student.getEmail(),
                otp);

        return "OTP sent successfully";

    }

    // VERIFY OTP

    @PostMapping("/verify-otp")

    public String verifyOtp(
            @RequestBody Map<String, String> data) {

        String studentId = data.get("studentId");

        String otp = data.get("otp");

        Student student = studentRepository
                .findByStudentId(studentId)
                .orElse(null);

        if (student == null) {

            return "Student not found";

        }

        if (!student.getResetOtp().equals(otp)) {

            return "Invalid OTP";

        }

        if (student.getOtpExpiry()
                .isBefore(LocalDateTime.now())) {

            return "OTP expired";

        }

        return "OTP verified";

    }

    // RESET PASSWORD

    @PostMapping("/reset-password")

    public String resetPassword(
            @RequestBody Map<String, String> data) {

        String studentId = data.get("studentId");

        String password = data.get("password");

        Student student = studentRepository
                .findByStudentId(studentId)
                .orElse(null);

        if (student == null) {

            return "Student not found";

        }

        student.setPassword(password);

        student.setResetOtp(null);

        student.setOtpExpiry(null);

        studentRepository.save(student);

        return "Password changed successfully";

    }

}