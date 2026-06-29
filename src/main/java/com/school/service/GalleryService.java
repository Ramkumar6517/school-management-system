package com.school.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.entity.Gallery;
import com.school.repository.GalleryRepository;



@Service
public class GalleryService {


    @Autowired
    private GalleryRepository repository;



    public Gallery save(Gallery gallery){

        return repository.save(gallery);

    }



    public List<Gallery> getByCategory(String category){

        return repository.findByCategory(category);

    }


}