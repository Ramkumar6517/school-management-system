package com.school.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.entity.Notice;
import com.school.repository.NoticeRepository;


@Service
public class NoticeService {


    @Autowired
    private NoticeRepository repo;



    // SAVE NOTICE

    public void saveNotice(Notice notice) {

        if(notice.getCreatedAt()==null){

            notice.setCreatedAt(LocalDateTime.now());

        }

        repo.save(notice);

    }







    // ADMIN -> ALL NOTICE

    public List<Notice> getAdminNotices() {

        return repo.findAll();

    }







    // STUDENT DASHBOARD NOTICE

    public List<Notice> getStudentNotices(String className) {


        List<Notice> notices =
                repo.findByTargetRoleIn(
                    Arrays.asList("student","all")
                );



        return notices.stream()

                .filter(n ->


                    n.getTargetClass()==null

                    ||

                    n.getTargetClass()
                    .equalsIgnoreCase("all")


                    ||

                    n.getTargetClass()
                    .replace("Class ","")
                    .equalsIgnoreCase(
                        className.replace("Class ","")
                    )


                )

                .toList();


    }







    // TEACHER DASHBOARD NOTICE

    public List<Notice> getTeacherNotices() {


        return repo.findByTargetRoleIn(

            Arrays.asList("teacher","all")

        );


    }







    // NOTICE BY SENDER

    public List<Notice> getNoticesByRole(String sender){


        return repo.findBySender(sender);


    }







    // GET SINGLE NOTICE FOR EDIT

    public Notice getById(Long id){


        return repo.findById(id)

                .orElse(null);


    }







    // DELETE NOTICE

    public void deleteNotice(Long id){


        repo.deleteById(id);


    }






    // UPDATE NOTICE

    public void updateNotice(
            Long id,
            String title,
            String message){


        Notice notice = getById(id);



        if(notice != null){


            notice.setTitle(title);

            notice.setMessage(message);


            repo.save(notice);


        }


    }



}