package com.school.dto;

public class NoticeForm {

    private String title;
    private String message;
    private String targetRole;
    private String targetClass;

    
    // getters and setters




    public String getTitle() {
        return title;
    }
    public String getMessage() {
        return message;
    }
    public String getTargetRole() {
        return targetRole;
    }
    public String getTargetClass() {
        return targetClass;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }
    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    
}