package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.service.NoticeService;

@Controller
@RequestMapping("/student")
public class StudentNoticeController {

    @Autowired
    private NoticeService service;
@GetMapping("/notices")
public String dashboard(@RequestParam String className, Model model) {
    model.addAttribute("notices",
            service.getStudentNotices(className));

    return "student-notice";
}
}