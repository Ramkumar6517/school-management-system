package com.school.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.ResultStatus;


public interface ResultStatusRepository 
extends JpaRepository<ResultStatus,Long>{

}