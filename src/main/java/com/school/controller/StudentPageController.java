package com.school.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.school.entity.Notice;
import com.school.entity.ResultStatus;
import com.school.entity.Student;
import com.school.repository.ResultStatusRepository;
import com.school.repository.StudentRepository;
import com.school.service.NoticeService;
import com.school.service.StudentService;

import jakarta.servlet.http.HttpSession;


@Controller
public class StudentPageController {


    @Autowired
    private StudentService studentService;


    @Autowired
    private StudentRepository studentRepository;

@Autowired
private NoticeService noticeService;
@Autowired
private ResultStatusRepository resultStatusRepository;

    // ================= REGISTER =================

    @PostMapping("/ui/register")
public String registerStudent(
        @ModelAttribute Student student,
        @RequestParam("photo") MultipartFile photo,
        @RequestParam("marksheet") MultipartFile marksheet,
        @RequestParam("aadhaarCard") MultipartFile aadhaarCard,
        Model model) throws Exception {


    student.setApplicationId("APP" + System.currentTimeMillis());
    student.setStatus("PENDING");

    // password = DOB
    student.setPassword(student.getDob());


    Student saved = studentService.saveStudent(
            student,
            photo,
            marksheet,
            aadhaarCard
    );


    model.addAttribute(
            "applicationId",
            saved.getApplicationId()
    );


    return "success";
}
    // ================= LOGIN PAGE =================


    @GetMapping("/login")
    public String loginPage(){

        return "login";

    }





    // ================= LOGIN =================


    @PostMapping("/login")
    public String loginStudent(
            @RequestParam String studentId,
            @RequestParam String password,
            HttpSession session,
            Model model){



        String result =
                studentService.login(
                studentId,
                password
                );



        if(result.equals("Login successful")){


            session.setAttribute(
                    "studentId",
                    studentId
            );


            return "redirect:/student/dashboard";

        }



        model.addAttribute(
                "error",
                result
        );


        return "login";

    }

@GetMapping("/student/dashboard")
public String dashboard(
        HttpSession session,
        Model model){


    String studentId =
            (String) session.getAttribute("studentId");


    if(studentId == null){
        return "redirect:/login";
    }



    Student student =
            studentRepository
            .findByStudentId(studentId)
            .orElse(null);



    if(student == null){
        return "redirect:/login";
    }



    List<Notice> list =
            noticeService.getStudentNotices(
                    student.getClassName()
            );



    boolean resultDeclared =
            resultStatusRepository.findById(1L)
            .map(ResultStatus::isDeclared)
            .orElse(false);



    System.out.println("RESULT STATUS = " + resultDeclared);



    model.addAttribute("student", student);

    model.addAttribute("notices", list);

    model.addAttribute("resultDeclared", resultDeclared);



    return "studentdashboard";

}


    // ================= CHANGE PASSWORD =================


    @GetMapping("/student/change-password")
    public String changePasswordPage(){

        return "change-password";

    }





    @PostMapping("/student/change-password")
    public String changePassword(
            @RequestParam String newPassword,
            HttpSession session){



        String studentId =
                (String)session.getAttribute("studentId");



        Student student =
                studentRepository
                .findByStudentId(studentId)
                .orElse(null);



        if(student!=null){


            student.setPassword(newPassword);


            studentRepository.save(student);

        }



        return "redirect:/student/dashboard";


    }

@GetMapping("/student/ajax-notices")
@ResponseBody
public String ajaxNotices(HttpSession session){


    String studentId =
        (String)session.getAttribute("studentId");


    Student student =
        studentRepository
        .findByStudentId(studentId)
        .orElse(null);



    if(student == null){

        return "";

    }



    List<Notice> notices =
        noticeService.getStudentNotices(
            student.getClassName()
        );



    String html="";


    for(Notice n : notices){


        html += """

        <div class="bg-white shadow-md rounded-xl p-5 border">

        <h3 class="text-lg font-bold text-blue-900">
        %s
        </h3>


        <p class="text-gray-700">
        %s
        </p>


        <p>
        👤 From: %s
        </p>


        <p>
        🏫 Class: %s
        </p>


        </div>

        """.formatted(
                n.getTitle(),
                n.getMessage(),
                n.getSender(),
                n.getTargetClass()
        );


    }



    return html;

}

}