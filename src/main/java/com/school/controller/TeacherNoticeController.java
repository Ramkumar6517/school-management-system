package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.dto.NoticeForm;
import com.school.entity.Notice;
import com.school.repository.NoticeRepository;
import com.school.service.NoticeService;


@Controller
@RequestMapping("/teacher")
public class TeacherNoticeController {


    @Autowired
    private NoticeService service;


    @Autowired
    private NoticeRepository repo;



    @GetMapping("/notices")
    public String noticePage(){

        return "teacher-notice";

    }





    // SEND NOTICE

    @PostMapping("/notices/send")
    @ResponseBody
    public String sendNotice(
            @ModelAttribute NoticeForm form){


        Notice n = new Notice();


        n.setTitle(form.getTitle());

        n.setMessage(form.getMessage());

        n.setSender("teacher");


        // teacher notice always student

        n.setTargetRole("student");



        if(form.getTargetClass()==null ||
           form.getTargetClass().isEmpty()){


            n.setTargetClass("all");

        }
        else{


            n.setTargetClass(form.getTargetClass());

        }



        service.saveNotice(n);


        return "success";

    }





    // ONLY TEACHER SENT NOTICE SHOW

    @GetMapping("/notices/list")
    @ResponseBody
    public List<Notice> teacherNotices(){


        return repo.findBySender("teacher");


    }







    // DELETE NOTICE

    @PostMapping("/notices/delete")
    @ResponseBody
    public String deleteNotice(
            @RequestParam Long id){


        service.deleteNotice(id);


        return "deleted";

    }








    // UPDATE NOTICE


   @PostMapping("/notices/update")
@ResponseBody
public String updateNotice(

        @RequestParam Long id,

        @RequestParam String title,

        @RequestParam String message

){


    Notice n = service.getById(id);



    if(n == null){

        return "notice not found";

    }



    n.setTitle(title);

    n.setMessage(message);



    service.saveNotice(n);



    return "updated";

}


}