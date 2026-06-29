package com.school.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.entity.Marks;
import com.school.entity.ResultStatus;
import com.school.entity.Student;
import com.school.repository.MarksRepository;
import com.school.repository.ResultStatusRepository;
import com.school.service.StudentService;

import jakarta.servlet.http.HttpSession;



@Controller
public class StudentController {



    @Autowired
    private StudentService studentService;


    @Autowired
    private MarksRepository marksRepository;



    @GetMapping("/student/showId")
    public String showId(
            @RequestParam(required=false) String studentId,
            Model model){


        if(studentId!=null){


            Student student =
                    studentService.findByStudentId(studentId);


            model.addAttribute(
                    "student",
                    student
            );

        }


        return "student-id-card";

    }

@Autowired
private ResultStatusRepository resultStatusRepository;



 @GetMapping("/student/results")
public String studentResults(
        HttpSession session,
        Model model
){

    String studentId =
            (String) session.getAttribute("studentId");


    if(studentId == null){
        return "redirect:/student/login";
    }


    Student student =
            studentService.findByStudentId(studentId);


    List<Marks> marks =
            marksRepository.findByStudent(student);



    ResultStatus status =
    resultStatusRepository.findById(1L)
    .orElse(new ResultStatus());



    if(status.isDeclared()){

        model.addAttribute(
            "marks",
            marks
        );

    }
    else{

        model.addAttribute(
            "marks",
            null
        );

    }



    model.addAttribute(
        "student",
        student
    );


    return "student/student-results";

}
}