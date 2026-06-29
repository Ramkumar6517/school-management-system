package com.school.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.Administrator;


public interface AdministratorRepository 
extends JpaRepository<Administrator, Long>{


    Administrator findByUsername(String username);


    Administrator findByEmail(String email);


}