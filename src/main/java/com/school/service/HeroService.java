package com.school.service;

import com.school.entity.HeroSection;

public interface HeroService {
    HeroSection getHero();
    HeroSection saveHero(HeroSection hero);
}