package com.school.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.HeaderSetting;



public interface HeaderRepository 
extends JpaRepository<HeaderSetting,Integer>{


}