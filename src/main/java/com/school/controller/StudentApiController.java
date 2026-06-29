package com.school.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.service.StudentService;

@RestController
@RequestMapping("/api")

public class StudentApiController {

    @Autowired
    private StudentService service;

    // ================= LOGIN API (AJAX) =================
    @PostMapping("/logins")
    public String login(@RequestBody Map<String, String> req) {

        return service.login(
                req.get("studentId"),
                req.get("password")
        );
    }

    // ================= ADMIN UNLOCK =================
    @PostMapping("/admin/unlock")
    public String unlock(@RequestBody Map<String, String> req) {

        return service.unlockStudent(req.get("studentId"));
    }

    // ================= ADMIN ENABLE / DISABLE =================
    @PostMapping("/admin/toggle")
    public String toggle(@RequestBody Map<String, String> req) {

        return service.toggleEnable(
                req.get("studentId"),
                Boolean.parseBoolean(req.get("status"))
        );
    }

    // ================= ADMIN LOCK =================

@PostMapping("/admin/lock")
public String lock(@RequestBody Map<String,String> req) {

    return service.lockStudent(
            req.get("studentId")
    );

}
}