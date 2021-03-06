package com.nzxs2.controller;

import com.nzxs2.dao.ArticleDao;
import com.nzxs2.dao.PageDao;
import com.nzxs2.domin.Article;
import com.nzxs2.domin.ArticleInfo;
import com.nzxs2.domin.Page;
import com.nzxs2.service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
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
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private PageDao pageDao;

    public static Logger logger = LoggerFactory.getLogger(CrawlerController.class);

    /**
     * function:爬取文章url
     */
    @RequestMapping("/crawlerArticleUrl")
    public String crawlerArticleUrl() {

        List<Page> pageList = pageDao.selectByStatus();//选前10页
        for (Page page : pageList) {
            List<Article> articles = crawlerService.getArticleUrls(page.getId());
            crawlerService.insertArticles(articles);
            pageDao.updateStatus(page.getId());
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }

    /**
     * function:爬取文章
     */
    @RequestMapping("/crawlerArticleInfo")
    public String crawlerArticleInfo() {

        List<Article> articles = articleDao.selectByStatus();
        for (int i = 0; i < articles.size(); i++) {
            Article art = articles.get(i);
            ArticleInfo articleInfo = crawlerService.getArticleInfo(art);
            crawlerService.insertArticleInfo(articleInfo);
            articleDao.updateStatus(articleInfo.getArticleId());
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }


}
