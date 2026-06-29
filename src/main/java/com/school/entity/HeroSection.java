package com.school.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hero_section")
public class HeroSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String heading;
    private String subHeading;

    // store comma separated images
    @Column(length = 2000)
    private String images;
   

    public HeroSection() {}
    

    public Long getId() { return id; }

    public String getHeading() { return heading; }
    public void setHeading(String heading) { this.heading = heading; }

    public String getSubHeading() { return subHeading; }
    public void setSubHeading(String subHeading) { this.subHeading = subHeading; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public void setId(Long id) {
        this.id = id;
    }
}