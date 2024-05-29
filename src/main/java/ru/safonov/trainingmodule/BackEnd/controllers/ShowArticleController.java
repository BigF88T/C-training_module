package ru.safonov.trainingmodule.BackEnd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("articles")
public class ShowArticleController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/article-1")
    public String article1(){
        return "/articles/article-1";
    }

    @GetMapping("/article-2")
    public String article2() {
        return "/articles/article-2";
    }

    @GetMapping("/article-3")
    public String article3() {
        return "/articles/article-3";
    }

    @GetMapping("/article-4")
    public String article4() {
        return "/articles/article-4";
    }

    @GetMapping("/article-5")
    public String article5() {
        return "/articles/article-5";
    }

    @GetMapping("/article-6")
    public String article6() {
        return "/articles/article-6";
    }

    @GetMapping("/article-7")
    public String article7() {
        return "/articles/article-7";
    }

    @GetMapping("/article-8")
    public String article8() {
        return "/articles/article-8";
    }

    @GetMapping("/article-9")
    public String article9(){
        return "/articles/article-9";
    }

}
