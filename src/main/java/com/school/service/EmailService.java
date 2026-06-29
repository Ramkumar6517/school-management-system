// package com.school.service;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.stereotype.Service;



// @Service
// public class EmailService {



// @Autowired
// private JavaMailSender mailSender;



// public void sendOtp(String email,String otp){


// SimpleMailMessage message = new SimpleMailMessage();


// message.setFrom("ramkumar651763@gmail.com");


// message.setTo(email);


// message.setSubject("Student Password Reset OTP");



// message.setText(

// "Hello Student,\n\n"

// +"Your OTP for password reset is: "

// +otp

// +"\n\n"

// +"This OTP is valid for 5 minutes."

// );



// mailSender.send(message);


// }



// }


package com.school.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailService {



@Autowired
private JavaMailSender mailSender;




// Student Forgot Password OTP

public void sendOtp(String email,String otp){


SimpleMailMessage message = new SimpleMailMessage();


message.setFrom("ramkumar651763@gmail.com");


message.setTo(email);


message.setSubject("Student Password Reset OTP");



message.setText(

"Hello Student,\n\n"

+"Your OTP for password reset is: "

+otp

+"\n\n"

+"This OTP is valid for 5 minutes."

);



mailSender.send(message);


}






// Administrator Login OTP

public void sendAdminOtp(String email,String otp){


SimpleMailMessage message = new SimpleMailMessage();


message.setFrom("ramkumar651763@gmail.com");


message.setTo(email);


message.setSubject("Administrator Login OTP");



message.setText(

"Hello Administrator,\n\n"

+"Your login OTP is: "

+otp

+"\n\n"

+"This OTP is valid for 5 minutes."

);



mailSender.send(message);


}



}