package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.entity.Notice;
import com.school.repository.NoticeRepository;
import com.school.service.NoticeService;


@Controller
@RequestMapping("/admin")
public class AdminNoticeController {


    @Autowired
    private NoticeService service;


    @Autowired
    private NoticeRepository repo;




    @GetMapping("/notices")
    public String noticePage(Model model) {


        model.addAttribute(
            "notices",
            service.getAdminNotices()
        );


        return "admin-notice";

    }






    @PostMapping("/notices/send")
    @ResponseBody
    public String sendNotice(
            @ModelAttribute Notice notice
    ){


        notice.setSender("admin");


        if(notice.getTargetClass()==null 
        || notice.getTargetClass().isEmpty()){


            notice.setTargetClass("all");

        }


        service.saveNotice(notice);



        return "success";

    }







    @PostMapping("/notices/delete")
    @ResponseBody
    public String deleteNotice(
            @RequestParam Long id
    ){


        repo.deleteById(id);


        return "deleted";

    }







    @PostMapping("/notices/update")
    @ResponseBody
    public String updateNotice(
            @RequestParam Long id,
            @RequestParam String title,
            @RequestParam String message
    ){



        Notice notice =
                repo.findById(id)
                .orElse(null);



        if(notice != null){


            notice.setTitle(title);

            notice.setMessage(message);


            repo.save(notice);


        }



        return "updated";


    }

@GetMapping("/notices/filter")
@ResponseBody
public List<Notice> filterNotices(
        @RequestParam String role){

    if(role.equals("all")){
        return service.getAdminNotices();
    }

    return service.getNoticesByRole(role);
}

}