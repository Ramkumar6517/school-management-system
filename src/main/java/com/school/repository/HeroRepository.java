package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.HeroSection;

public interface HeroRepository extends JpaRepository<HeroSection, Integer> {

}