package com.nzxs2.job;

import com.nzxs2.constant.CacheConstant;
import com.nzxs2.dao.ArticleDao;
import com.nzxs2.dao.PageDao;
import com.nzxs2.domin.Article;
import com.nzxs2.domin.ArticleInfo;
import com.nzxs2.domin.Page;
import com.nzxs2.service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Ryan
 * @Date 2017/9/15 13:45
 * @Function
 */
@Component
@Configurable
//@EnableScheduling
public class SnzxJob {
    public static Logger logger = LoggerFactory.getLogger(SnzxJob.class);
    @Autowired
    private CrawlerService crawlerService;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private PageDao pageDao;

    @Scheduled(cron = "0 05 */1  * * * ")
    public void crawlerArticleUrl() {

        logger.info("==========爬去url任务启动============");
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
        logger.info("==========爬去url任务结束============");
    }

    @Scheduled(cron = "0 05 */1 * * * ")
    public void crawlerArticleInfo() {

        logger.info("==========爬去文章info任务启动============");
        List<Article> articles = articleDao.selectByStatus();
        for (int i = 0; i < articles.size(); i++) {
            Article art = articles.get(i);
            ArticleInfo articleInfo = crawlerService.getArticleInfo(art);
            crawlerService.insertArticleInfo(articleInfo);
            articleDao.updateStatus(articleInfo.getArticleId());
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("==========爬去文章info任务结束============");
    }


}
