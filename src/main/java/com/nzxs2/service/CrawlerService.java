package com.nzxs2.service;

import com.nzxs2.domin.Article;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/10.
 */
public interface CrawlerService {


    void insertArticles(List<Article> articleList);

    List<Article> getArticleUrls();
}
