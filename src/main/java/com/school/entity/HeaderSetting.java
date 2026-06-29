package com.school.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="header_setting")
public class HeaderSetting {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String affiliation;

    private String affiliationNo;

    private String email;

    private String location;

    private String logo;

    private String phone;

    private String schoolName;



    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getLogo() {
        return logo;
    }


    public void setLogo(String logo) {
        this.logo = logo;
    }


    public String getSchoolName() {
        return schoolName;
    }


    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }


    public String getAffiliation() {
        return affiliation;
    }


    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }


    public String getAffiliationNo() {
        return affiliationNo;
    }


    public void setAffiliationNo(String affiliationNo) {
        this.affiliationNo = affiliationNo;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

}