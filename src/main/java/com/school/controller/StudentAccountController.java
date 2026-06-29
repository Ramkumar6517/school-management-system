package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.entity.Student;
import com.school.repository.StudentRepository;


@Controller
public class StudentAccountController {


    @Autowired
    private StudentRepository studentRepository;



    @GetMapping("/student/account")
    public String page(){

        return "student-disable";
    }



    @GetMapping("/student/disable")
public String searchStudent(
        @RequestParam(value="studentId", required=false) String studentId,
        Model model){


    if(studentId != null){

        Student student = studentRepository
                .findByStudentId(studentId)
                .orElse(null);


        model.addAttribute("student", student);

    }


    return "student-disable";

}
}