package com.nzxs2.service.impl;

import com.nzxs2.dao.ArticleDao;
import com.nzxs2.domin.Article;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/10.
 */
@Service
public class CrawlerServiceImpl implements CrawlerService {
    @Autowired
    private ArticleDao articleDao;

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
    public List<Article> getArticleUrls() {

        //todo test 1
        List<Article> articles = new ArrayList<>();
        String url = parseUrl(1, NZXS2_PAGEURL);
        String result = doCrawler(url);
        parsePageUrl(result, articles);
        return articles;
    }

    private String parseUrl(Integer articleNum,String url) {
        return String.format(url, articleNum);
    }

    /**
      *function:解析列表页中的文章地址
     */
    private void parsePageUrl(String html,List<Article> articleUrlTitle) {

        Document doc = Jsoup.parse(html);
        Elements content = doc.getElementsByClass("listtitletxt");
        for (Element element : content) {
            Article article = new Article();
            Element el = element.select("a").first();
            article.setTitle(el.text());
            article.setUrl(el.attr("href"));
            articleUrlTitle.add(article);
//            articleUrlTitle.put(el.attr("href"), el.text());
        }
    }

    /**
      *function:爬虫方法
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
}
