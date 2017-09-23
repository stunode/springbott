package com.nzxs2.service.impl;

import com.nzxs2.constant.CacheConstant;
import com.nzxs2.dao.ArticleInfoDao;
import com.nzxs2.domin.ArticleInfo;
import com.nzxs2.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author Ryan
 * @Date 2017/9/16 15:12
 * @Function
 */
@Service
@CacheConfig(cacheNames = "articleInfo")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ArticleInfoServiceImpl implements ArticleInfoService {

    @Autowired
    private ArticleInfoDao articleInfoDao;

    @Override
    @CacheEvict(key = CacheConstant.ENTITY_ARTICLE_LIST)
    public void insert(ArticleInfo articleInfo) {
        articleInfoDao.insert(articleInfo);
    }

    @Override
    @Cacheable(key = CacheConstant.ENTITY_ARTICLE_LIST)
    public List<ArticleInfo> selectArticleInfos(Integer page) {
//        PageHelper.startPage(page, 50); //服务端分页
        List<ArticleInfo> articleInfos = articleInfoDao.selectArticleInfos();
        try {
            Collections.sort(articleInfos, new Comparator<ArticleInfo>() {
                @Override
                public int compare(ArticleInfo o1, ArticleInfo o2) {
                    return o2.getId().compareTo(o1.getId());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleInfos;
    }

    @Override
    @Cacheable(key = CacheConstant.ENTITY_ARTICLE_ID + "#articleId")
    public ArticleInfo selectArticleDetail(Integer articleId) {
        return articleInfoDao.selectArticleDetail(articleId);
    }
}
