package com.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.school")
public class SchoolManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolManagementSystemApplication.class, args);
		System.out.println("hello spring boot");
    }
}
