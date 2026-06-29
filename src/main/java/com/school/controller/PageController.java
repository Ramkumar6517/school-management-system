package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
@GetMapping("/contact")
public String contact() {
    return "fragments/contact :: contact";
}

    
}
