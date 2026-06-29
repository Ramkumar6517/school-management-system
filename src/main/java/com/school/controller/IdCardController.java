package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.school.entity.HeaderSetting;
import com.school.entity.Student;
import com.school.repository.HeaderRepository;
import com.school.repository.StudentRepository;

@Controller
public class IdCardController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
private HeaderRepository headerRepository;

    // LIST PAGE
    @GetMapping("/idcard/student")
    public String showIdCardList(Model model) {

        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);

        return "student-id-list";
    }
@GetMapping("/admin/generate-id/{id}")
public String generateStudentId(@PathVariable Long id) {


    Student student = studentRepository.findById(id).orElse(null);


    if(student != null 
        && student.getStudentId() == null
        && "APPROVED".equals(student.getStatus())) {


        // Header table se affiliation number lena
        HeaderSetting header = headerRepository.findById(1)
                .orElse(null);


        String affiliationNo = "";

        if(header != null) {
            affiliationNo = header.getAffiliationNo();
        }


        // Current Year
        String year = String.valueOf(java.time.Year.now().getValue());

        // Unique Number
        String uniqueNumber = String.format("%03d", student.getId());


        // Generate Student ID
        String uniqueId = "STU"
                + affiliationNo
                + year
                + uniqueNumber;


        student.setStudentId(uniqueId);

        studentRepository.save(student);
    }


    return "redirect:/admin/student-id-list";
}

}