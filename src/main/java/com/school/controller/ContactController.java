package com.school.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.ApiResponse;
import com.school.entity.ContactMessage;
import com.school.service.ContactMessageService;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactMessageService service;

    public ContactController(ContactMessageService service) {
        this.service = service;
        System.out.println("🔥 API HIT HO GAYA");
    }

@PostMapping("/send")
public ApiResponse sendMessage(@RequestBody ContactMessage msg) {

    // 👇 DEBUG HERE (PASTE THIS)
    System.out.println("NAME: " + msg.getName());
    System.out.println("EMAIL: " + msg.getEmail());
    System.out.println("MESSAGE: " + msg.getMessage());

    service.save(msg);

    return new ApiResponse("success", "Message sent successfully!");
}

}