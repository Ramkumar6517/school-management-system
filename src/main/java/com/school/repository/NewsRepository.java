package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findTop5ByOrderByIdDesc();
}