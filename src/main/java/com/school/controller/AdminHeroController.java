package com.school.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.school.entity.HeroSection;
import com.school.repository.HeroRepository;

@Controller
@RequestMapping("/admin/hero")
public class AdminHeroController {


    @Autowired
    private HeroRepository heroRepository;



    @GetMapping("/edit")
    public String editHero(Model model){


        HeroSection hero =
                heroRepository.findById(1)
                .orElse(new HeroSection());


        model.addAttribute("hero", hero);


        return "admin/admin-hero";
    }


@PostMapping("/save")
public String saveHero(
        @RequestParam("heading") String heading,
        @RequestParam("subHeading") String subHeading,
        @RequestParam("images") MultipartFile[] images
) throws IOException {


    HeroSection oldHero =
            heroRepository.findById(1)
            .orElse(new HeroSection());


    oldHero.setId(1L);

    oldHero.setHeading(heading);

    oldHero.setSubHeading(subHeading);



    List<String> fileNames = new ArrayList<>();


    String uploadPath =
    System.getProperty("user.dir")
    + File.separator
    + "src/main/resources/static/images/";



    File folder = new File(uploadPath);



    if(!folder.exists()){

        folder.mkdirs();

    }



    for(MultipartFile image : images){


        if(!image.isEmpty()){


            String fileName =
            System.currentTimeMillis()
            + "_"
            + image.getOriginalFilename();



            File saveFile =
            new File(folder, fileName);



            image.transferTo(saveFile);



            fileNames.add(fileName);



        }

    }



    if(!fileNames.isEmpty()){


        oldHero.setImages(
            String.join(",", fileNames)
        );


    }



    heroRepository.save(oldHero);



    return "redirect:/";

}
}