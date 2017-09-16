package com.nzxs2.service.impl;

import com.nzxs2.dao.ArticleDao;
import com.nzxs2.dao.ArticleInfoDao;
import com.nzxs2.domin.Article;
import com.nzxs2.domin.ArticleInfo;
import com.nzxs2.service.CrawlerService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by admin on 2017/9/10.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CrawlerServiceImpl implements CrawlerService {
    public static org.slf4j.Logger logger = LoggerFactory.getLogger(CrawlerServiceImpl.class);
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleInfoDao articleInfoDao;

    //nzxs2网站的中文编码
    private static String NZXS2_CHARSET = "gb2312";
    private static final String NZXS2_PAGEURL = "http://www.nzxs2.com/nzxs/p_%s.html";


    @Override
    public void insertArticles(List<Article> articleList) {
        for (Article article : articleList) {
            articleDao.insert(article);
        }
    }

    @Override
    public void insertArticleInfo(ArticleInfo articleInfo) {
        articleInfoDao.insert(articleInfo);

    }

    @Override
    public List<Article> getArticleUrls(Integer pageNum) {

        List<Article> articles = new ArrayList<>();
        String url = parseUrl(pageNum, NZXS2_PAGEURL);
        String result = doCrawler(url);
        parsePageUrl(result, articles);
        return articles;
    }

    @Override
    public ArticleInfo getArticleInfo(Article article) {

        String infoUrl = article.getUrl();
        String result = doCrawler(infoUrl);
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle(article.getTitle());
        articleInfo.setArticleId(article.getId());
        parseArticleInfo(result, articleInfo);
        return articleInfo;
    }

    @Override
    public void transactionTest() {
        Article article = new Article();
        article.setTitle("asd");
        article.setUrl("sda");

        articleDao.insert(article);
        articleDao.insert(article);
    }

    private String parseUrl(Integer articleNum, String url) {
        return String.format(url, articleNum);
    }

    /**
     * function:解析列表页中的文章地址
     */
    private void parsePageUrl(String html, List<Article> articleUrlTitle) {

        if (html == null) {
            logger.info("===========html为空，文章rul解析失败===============");
            return;
        }
        Document doc = Jsoup.parse(html);
        Elements content = doc.getElementsByClass("listtitletxt");
        for (Element element : content) {
            Article article = new Article();
            Element el = element.select("a").first();
            article.setTitle(el.text());
            article.setUrl(el.attr("href"));
            articleUrlTitle.add(article);
        }
    }

    /**
     * function:解析文章内容
     */
    private void parseArticleInfo(String html, ArticleInfo articleInfo) {
        if (html == null) {
            logger.info("===========html为空，文章info解析失败===============");
            return;
        }
        Document doc = Jsoup.parse(html);
        Elements content = doc.getElementsByClass("n_bd");
        Element el = content.first();
        String art = el.text();
        articleInfo.setArticleText(art);
    }

    /**
     * function:爬虫方法
     */
    private String doCrawler(String url) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpGet get = new HttpGet(url);
        HttpResponse httpResponse = null;


        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(120000).setConnectionRequestTimeout(100000)
                .setSocketTimeout(120000).build();
        get.setConfig(requestConfig);

        try {
            httpResponse = httpClient.execute(get);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, NZXS2_CHARSET);
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
//        CrawlerServiceImpl crawlerService = new CrawlerServiceImpl();
//        String html = crawlerService.doCrawler("http://www.nzxs2.com/nzxs/24900.html");
//        ArticleInfo a = new ArticleInfo();
//        crawlerService.parseArticleInfo(html, a);
//        System.out.println(a);

//        String a = new String("sad dsad dsad dsad");
//        String b = a.split(" ")[3];
//        System.out.println(b);
    }
}
