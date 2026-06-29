package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.entity.News;
import com.school.repository.NewsRepository;
import com.school.service.NewsService;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
private NewsRepository newsRepository;

    // Admin form page
    @GetMapping("/change/form")
    public String newsForm(Model model) {
        model.addAttribute("news", new News());
        return "admin/news_form";
    }

    // Save news
    @PostMapping("/save/news")
@ResponseBody
public News saveNews(@RequestBody News news) {
    return newsService.save(news);
}

    // index page load news
    @GetMapping("/news")
    public String index(Model model) {
        model.addAttribute("newsList", newsService.getLatestNews());
        return "index";
    }

//     @GetMapping("/delete/news/{id}")
// public String deleteNews(@PathVariable Long id) {

//     newsService.deleteNews(id);

//     return "redirect:/change/news";
// }

@PostMapping("/delete/news/{id}")
@ResponseBody
public void deleteNews(@PathVariable Long id) {
    newsService.delete(id);
}
@GetMapping("/change/news")
public String newsPage(Model model) {

    model.addAttribute("news", new News());

    model.addAttribute("newsList", newsRepository.findAll()); // 🔥 THIS IS REQUIRED

    return "admin/news_form";
}
}