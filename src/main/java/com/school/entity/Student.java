package com.school.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Info
    @NotBlank(message = "Student name is required")
    private String studentName;

    @NotBlank(message = "Father name is required")
    private String fatherName;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Column(nullable = false)
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobile;

    @Pattern(regexp = "^[0-9]{12}$", message = "Aadhaar must be 12 digits")
    @Column(name = "aadhar")
    private String aadhar;

    @NotBlank(message = "Date of birth is required")
    private String dob;

    @NotBlank(message = "Class name is required")
    private String className;

    private String address;

    // System fields
    private String applicationId;
    private String status;

    // File paths
    private String photoPath;
    private String marksheetPath;
    private String aadhaarCardPath;

    // Login
    @Column(name = "password")
    @NotBlank(message = "Password is required")
    private String password;

    @Column(unique = true)
    private String studentId;
    @Column
    private LocalDateTime otpExpiry;

    @Column
    private boolean locked = false;

    @Column
    private int failedAttempts = 0;
    @Column
    private boolean enabled = true;
    @Column
    private String resetOtp;

    

    // ---------------- GETTERS & SETTERS ----------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getMarksheetPath() {
        return marksheetPath;
    }

    public void setMarksheetPath(String marksheetPath) {
        this.marksheetPath = marksheetPath;
    }

    public String getAadhaarCardPath() {
        return aadhaarCardPath;
    }

    public void setAadhaarCardPath(String aadhaarCardPath) {
        this.aadhaarCardPath = aadhaarCardPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setResetOtp(String resetOtp) {
        this.resetOtp = resetOtp;
    }

    public void setOtpExpiry(LocalDateTime otpExpiry) {
        this.otpExpiry = otpExpiry;
    }

    public String getResetOtp() {
        return resetOtp;
    }

    public LocalDateTime getOtpExpiry() {
        return otpExpiry;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public boolean isEnabled() {
        return enabled;
    }
}