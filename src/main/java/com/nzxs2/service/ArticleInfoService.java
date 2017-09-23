package com.nzxs2.service;

import com.nzxs2.domin.ArticleInfo;

import java.util.List;

/**
 * @Author Ryan
 * @Date 2017/9/16 15:09
 * @Function
 */
public interface ArticleInfoService {

    void insert(ArticleInfo articleInfo);


    List<ArticleInfo> selectArticleInfos(Integer page);


    ArticleInfo selectArticleDetail(Integer articleId);
}
