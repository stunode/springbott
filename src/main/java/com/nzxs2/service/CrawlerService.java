package com.nzxs2.service;

import com.nzxs2.domin.Article;
import com.nzxs2.domin.ArticleInfo;

import java.util.List;


public interface CrawlerService {


    void insertArticles(List<Article> articleList);

    List<Article> getArticleUrls(Integer pageNum);

    void insertArticleInfo(ArticleInfo articleInfo);

    ArticleInfo getArticleInfo(Article article);

    void transactionTest();



}
