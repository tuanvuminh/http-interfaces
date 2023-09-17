package com.backend.articlesservice.controller;

import com.backend.articlesservice.model.Article;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final List<Article> articles = new ArrayList<>();

    @GetMapping
    public List<Article> findAll() {
        return articles;
    }

    @GetMapping("/{id}")
    public Optional<Article> findById(@PathVariable Integer id) {
        return articles.stream().filter(article -> article.id().equals(id)).findFirst();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Article article) {
        articles.add(article);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Article article, @PathVariable Integer id) {
        Optional<Article> postToUpdate = articles.stream().filter(article1 -> article1.equals(id)).findFirst();
        postToUpdate.ifPresent(value -> articles.set(articles.indexOf(value), article));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        articles.removeIf(article -> article.id().equals(id));
    }

    @PostConstruct
    private void init() {
        Article article = new Article(1, "Post 1", "Hello");
        articles.add(article);
    }
}
