package com.backend.contentservice.controller;

import com.backend.contentservice.model.Article;
import com.backend.contentservice.service.ArticleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ArticleClient articleClient;

    @Autowired
    public ContentController(ArticleClient articleClient) {
        this.articleClient = articleClient;
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> findAllArticles() {
        return articleClient.findAll();
    }

    @GetMapping("/articles/{id}")
    public Optional<Article> findById(@PathVariable Integer id) {
        return articleClient.findOne(id);
    }

    @PostMapping("/articles")
    public void create(@RequestBody Article article) {
        articleClient.create(article);
    }

    @PutMapping("/articles/{id}")
    public void update(@RequestBody Article article, @PathVariable Integer id) {
        articleClient.update(article,id);
    }

    @DeleteMapping("/articles/{id}")
    public void delete(@PathVariable Integer id) {
        articleClient.delete(id);
    }
}
