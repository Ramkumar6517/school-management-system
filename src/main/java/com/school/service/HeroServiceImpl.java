package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.entity.HeroSection;
import com.school.repository.HeroRepository;

@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroRepository repo;

    @Override
    public HeroSection getHero() {
        return repo.findAll().stream().findFirst().orElse(null);
    }

    @Override
    public HeroSection saveHero(HeroSection hero) {
        return repo.save(hero);
    }
}