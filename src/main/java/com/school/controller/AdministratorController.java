package com.school.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.entity.Administrator;
import com.school.repository.AdministratorRepository;
import com.school.service.EmailService;
import com.school.service.OtpService;

import jakarta.servlet.http.HttpSession;


@Controller
public class AdministratorController {


    @Autowired
    private AdministratorRepository administratorRepository;


    @Autowired
    private EmailService emailService;


    @Autowired
    private OtpService otpService;



    // Login Page
    @GetMapping("/Administrator/login")
    public String loginPage(){

        return "administrator-login";
    }



    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session){


        Administrator admin =
                (Administrator) session.getAttribute("admin");


        if(admin == null){

            return "redirect:/Administrator/login";
        }


        return "admin-dashboard";
    }




    // Login Check
    @PostMapping("/Administrator/login")
    @ResponseBody
    public String login(
            @RequestBody Administrator administrator,
            HttpSession session
    ){


        Administrator admin =
                administratorRepository.findByUsername(
                        administrator.getUsername()
                );



        if(admin != null &&
           admin.getPassword()
           .equals(administrator.getPassword())){


            String otp =
                    otpService.generateOTP();



            session.setAttribute(
                    "adminOtp",
                    otp
            );


            session.setAttribute(
                    "tempAdmin",
                    admin
            );



            emailService.sendAdminOtp(
                    admin.getEmail(),
                    otp
            );



            return "success";
        }



        return "failed";
    }




    // OTP Verify
    @PostMapping("/Administrator/verify")
    @ResponseBody
    public String verifyOtp(
            @RequestBody Map<String,String> data,
            HttpSession session
    ){


        String otp =
                data.get("otp");



        String savedOtp =
                (String) session.getAttribute("adminOtp");



        if(savedOtp != null &&
           savedOtp.equals(otp)){



            Administrator admin =
                    (Administrator)
                    session.getAttribute("tempAdmin");



            session.setAttribute(
                    "admin",
                    admin
            );



            session.removeAttribute(
                    "adminOtp"
            );


            session.removeAttribute(
                    "tempAdmin"
            );


            return "success";
        }



        return "failed";
    }




    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session){


        session.invalidate();


        return "administrator-login";
    }

    @GetMapping("/Administrator/forgot-password")
public String forgotPasswordPage(){

    return "forgot-password";

}
@PostMapping("/Administrator/send-reset-otp")
@ResponseBody
public String sendResetOtp(
        @RequestBody Map<String,String> data,
        HttpSession session
){

    String email=data.get("email");


    System.out.println("Email Received: "+email);



    Administrator admin =
        administratorRepository.findByEmail(email);



    System.out.println("Admin Found: "+admin);



    if(admin != null){

        String otp = otpService.generateOTP();


        session.setAttribute("resetOtp",otp);

        session.setAttribute("resetAdmin",admin);


        emailService.sendAdminOtp(
                email,
                otp
        );


        return "success";
    }


    return "failed";
}
@PostMapping("/Administrator/reset-password")
@ResponseBody
public String resetPassword(
        @RequestBody Map<String,String> data,
        HttpSession session
){


    String otp = data.get("otp");

    String password = data.get("password");



    String savedOtp =
        (String)session.getAttribute("resetOtp");



    if(savedOtp != null &&
       savedOtp.equals(otp)){



        Administrator admin =
        (Administrator)
        session.getAttribute("resetAdmin");



        admin.setPassword(password);


        administratorRepository.save(admin);



        session.removeAttribute("resetOtp");


        return "success";

    }



    return "failed";

}

}