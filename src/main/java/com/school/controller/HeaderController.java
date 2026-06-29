package com.school.controller;


import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.school.entity.HeaderSetting;
import com.school.entity.HeroSection;
import com.school.repository.HeaderRepository;
import com.school.repository.HeroRepository;
import com.school.service.NewsService;


@Controller
public class HeaderController {


    @Autowired
    private HeaderRepository headerRepository;


    @Autowired
    private HeroRepository heroRepository;

@Autowired
private NewsService newsService;

  @GetMapping("/")
public String home(Model model){

    HeaderSetting header =
            headerRepository.findById(1)
            .orElse(new HeaderSetting());

    HeroSection hero =
            heroRepository.findById(1)
            .orElse(new HeroSection());

    // 🔥 ADD THIS LINE (IMPORTANT)
    model.addAttribute("newsList", newsService.getLatestNews());

    model.addAttribute("header", header);
    model.addAttribute("hero", hero);

    return "index";
}



    @GetMapping("/change/header")
    public String headerPage(Model model){


        HeaderSetting header =
                headerRepository.findById(1)
                .orElse(new HeaderSetting());


        model.addAttribute("header", header);


        return "admin/header";
    }




    @PostMapping("/update/header")
    public String updateHeader(
            @ModelAttribute HeaderSetting header,
            @RequestParam("image") MultipartFile image
    ) throws IOException {



        HeaderSetting oldHeader =
                headerRepository.findById(1)
                .orElse(new HeaderSetting());


        oldHeader.setId(1);



        if(!image.isEmpty()){


            String fileName =
                    image.getOriginalFilename();



            String uploadDir =
                    System.getProperty("user.dir")
                    + "/uploads/";



            File folder =
                    new File(uploadDir);



            if(!folder.exists()){
                folder.mkdirs();
            }



            File file =
                    new File(uploadDir + fileName);



            image.transferTo(file);



            oldHeader.setLogo(fileName);

        }



        oldHeader.setSchoolName(header.getSchoolName());
        oldHeader.setAffiliation(header.getAffiliation());
        oldHeader.setAffiliationNo(header.getAffiliationNo());
        oldHeader.setEmail(header.getEmail());
        oldHeader.setLocation(header.getLocation());
        oldHeader.setPhone(header.getPhone());



        headerRepository.save(oldHeader);



        return "redirect:/";

    }

}