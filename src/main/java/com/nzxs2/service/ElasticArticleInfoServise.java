package com.nzxs2.service;

import com.nzxs2.domin.ArticleInfo;

import java.util.List;

/**
 * @Author Ryan
 * @Date 2017/9/20 20:18
 * @Function
 */
public interface ElasticArticleInfoServise {
    ArticleInfo queryArticleInfoById(Integer id);

    ArticleInfo queryArticleInfoByTitle(String title);

    List<ArticleInfo> queryArticleByElastic(String queryParam);

}
