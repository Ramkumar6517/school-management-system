package com.school.service;

import com.school.entity.Teacher;

public interface TeacherService {


    Teacher saveTeacher(Teacher teacher);


    Teacher getTeacherById(Long id);


    void deleteTeacher(Long id);


    void sendCredentials(
            String email,
            String mobile,
            String teacherId,
            String password
    );

}