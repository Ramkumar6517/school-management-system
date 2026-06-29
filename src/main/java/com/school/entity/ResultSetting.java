package com.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ResultSetting {

    @Id
    private Long id;

    private boolean published;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id=id;
    }


    public boolean isPublished() {
        return published;
    }


    public void setPublished(boolean published) {
        this.published=published;
    }
}