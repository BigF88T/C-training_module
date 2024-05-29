package ru.safonov.trainingmodule.BackEnd.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.safonov.trainingmodule.BackEnd.models.Article;
import ru.safonov.trainingmodule.BackEnd.models.Views;
import ru.safonov.trainingmodule.BackEnd.repositories.ArticlesRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("article")
public class ArticlesController {
    private final ArticlesRepository articlesRepository;

    @Autowired
    public ArticlesController(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Article> index(){
        return articlesRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullArticle.class)
    public Article getOne(@PathVariable("id") Article article) {
        return article;
    }

    @PostMapping
    public Article save(@RequestBody Article article) {
        article.setCreationDate(LocalDateTime.now());
        return articlesRepository.save(article);
    }

    @PutMapping("{id}")
    public Article update(@PathVariable("id") Article articleFromDb,
                          @RequestBody Article article
    ) {
        BeanUtils.copyProperties(article, articleFromDb, "id");

        return articlesRepository.save(articleFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Article article) {
        articlesRepository.delete(article);
    }
}
