package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/check-status")
    public String checkPage() {
        return "check-status";
    }
    @GetMapping("/admin-main")
    public String adminMainDashboard() {
        return "admin-dashboard";
    }
    @GetMapping("/admin-controller")
    public String adminControllerDashboard() {
        return "admin-controller";
    }
    

}

