package com.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ResultStatus {


    @Id
    private Long id = 1L;


    private boolean declared = false;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id=id;
    }


    public boolean isDeclared() {
        return declared;
    }


    public void setDeclared(boolean declared) {
        this.declared=declared;
    }

}