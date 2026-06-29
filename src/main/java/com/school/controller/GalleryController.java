package com.school.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.school.entity.Gallery;
import com.school.repository.GalleryRepository;
import com.school.service.GalleryService;

@Controller
public class GalleryController {

    @Autowired
    private GalleryService service;
    @Autowired
private GalleryRepository galleryRepository;

    // ADMIN PAGE

    @GetMapping("/admin/gallery")
    public String galleryPage() {

        return "admin/gallery";

    }
  @GetMapping("/api/gallery")
@ResponseBody
public List<Gallery> getGallery(
        @RequestParam String category
){

    return galleryRepository.findByCategory(category);

}

@PostMapping("/admin/gallery/save")
@ResponseBody
public String saveGallery(
        @RequestParam("file") MultipartFile file,
        @RequestParam("category") String category
) throws IOException {


    String uploadDir = System.getProperty("user.dir")
            + "/src/main/resources/static/uploads/";


    File folder = new File(uploadDir);

    if(!folder.exists()){
        folder.mkdirs();
    }


    String fileName = System.currentTimeMillis()
            + "_" 
            + file.getOriginalFilename();


    File saveFile = new File(uploadDir + fileName);


    file.transferTo(saveFile);



    Gallery gallery = new Gallery();

    gallery.setImageName(fileName);

 gallery.setImagePath("/gallery/" + fileName);

    gallery.setCategory(category);


    galleryRepository.save(gallery);



    return "success";

}

    // USER SIDE

@GetMapping("/gallery")
public String gallery(Model model){

    model.addAttribute("category", "GALLERY");

    return "gallery";

}

   @GetMapping("/sports")
public String sports(Model model) {


    model.addAttribute("category", "SPORTS");

    return "gallery";

}

   @GetMapping("/events")
public String events(Model model) {


    model.addAttribute("category", "EVENT");

    return "gallery";

}




@DeleteMapping("/admin/gallery/delete/{id}")
public ResponseEntity<?> deleteGallery(
        @PathVariable Long id
){

    Gallery gallery = galleryRepository
            .findById(id)
            .orElse(null);


    if(gallery == null){

        return ResponseEntity
                .notFound()
                .build();

    }


    // delete image from folder

    String path =
    "src/main/resources/static/uploads/"
    + gallery.getImageName();


    File file = new File(path);


    if(file.exists()){

        file.delete();

    }



    // delete database record

    galleryRepository.delete(gallery);



    return ResponseEntity.ok("deleted");

}
@GetMapping("/admin/gallery/upload")
public String uploadPage(Model model){

    model.addAttribute(
       "galleryList",
       galleryRepository.findAll()
    );

    return "admin/gallery-upload";
}
}