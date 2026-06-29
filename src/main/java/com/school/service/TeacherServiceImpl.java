package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.school.entity.Teacher;
import com.school.repository.TeacherRepository;

@Service
public class TeacherServiceImpl
        implements TeacherService {

    @Autowired
    private TeacherRepository repo;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Teacher saveTeacher(Teacher teacher) {

        return repo.save(teacher);

    }

    @Override
    public Teacher getTeacherById(Long id) {

        return repo.findById(id).orElse(null);

    }

    @Override
    public void deleteTeacher(Long id) {

        repo.deleteById(id);

    }

    @Override
    public void sendCredentials(
            String email,
            String mobile,
            String teacherId,
            String password) {

        try {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(email);

            message.setSubject(
                    "Teacher Login Credentials");

            message.setText(

                    "Welcome Teacher\n\n" +

                            "Your Login Details:\n\n" +

                            "Teacher ID : " + teacherId +

                            "\nPassword : " + password

            );

            mailSender.send(message);

            System.out.println(
                    "Email Sent Successfully");

        } catch (Exception e) {

            System.out.println(
                    "Mail Error : " + e.getMessage());

        }

        // SMS API later connect hoga

        System.out.println(
                "SMS : " + mobile +
                        " ID=" + teacherId +
                        " PASS=" + password);

    }

}