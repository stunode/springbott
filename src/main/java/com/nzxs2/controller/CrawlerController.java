package com.nzxs2.controller;

import com.nzxs2.domin.Article;
import com.nzxs2.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by admin on 2017/9/10.
 */

@Controller
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping("/crawler")
    public String crawler() {

        List<Article> articles = crawlerService.getArticleUrls();
        crawlerService.insertArticles(articles);
        return "index";
    }

}
