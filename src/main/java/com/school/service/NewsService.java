package com.school.service;

import java.util.List;

import com.school.entity.News;

public interface NewsService {

    News save(News news);

    void delete(Long id);

    List<News> getAllNews();

    List<News> getLatestNews();  // ✔ must exist
}