package com.school.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.Gallery;


public interface GalleryRepository extends JpaRepository<Gallery, Long>{


    List<Gallery> findByCategory(String category);

}