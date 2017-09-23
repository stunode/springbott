import com.nzxs2.Application;
import com.nzxs2.dao.ArticleDao;
import com.nzxs2.dao.ArticleInfoDao;
import com.nzxs2.dao.PageDao;
import com.nzxs2.domin.Article;
import com.nzxs2.domin.ArticleInfo;
import com.nzxs2.domin.Page;
import com.nzxs2.rabbitmq.HelloSender1;
import com.nzxs2.repository.ElasticArticleInfoRepository;
import com.nzxs2.service.ArticleInfoService;
import com.nzxs2.service.ElasticArticleInfoServise;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @Author Ryan
 * @Date 2017/9/10 23:28
 * @Function
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class CrawlerTest {

    @Autowired
    ArticleDao articleDao;
    @Autowired
    PageDao pageDao;
    @Autowired
    ArticleInfoDao articleInfoDao;
    @Autowired
    ArticleInfoService articleInfoService;
    @Autowired
    HelloSender1 helloSender1;
    @Autowired
    private ElasticArticleInfoRepository elasticArticleInfoRepository;
    @Autowired
    private ElasticArticleInfoServise elasticArticleInfoServise;




    @Test
    public void getArticleListTest() {
        List<Article> articleList = articleDao.selectByStatus();
        System.out.println(articleList);
    }
    @Test
    public void getPage() {
        List<Page> pageList = pageDao.selectByStatus();//选前10页
        System.out.println(pageList);
    }

    /**
      *function:文章列表查询
     */
    @Test
    public void getArticleInfos() {
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
    }

    @Test
    public void articleDetail() {
        ArticleInfo a = articleInfoDao.selectArticleDetail(32);

    }

    @Test
    public void articleInsert() {
        ArticleInfo articleInfo = new ArticleInfo();

        articleInfo.setArticleId(233);
        articleInfo.setTitle("test");

        articleInfoService.insert(articleInfo);

    }

    @Test
    public void testEs() {


//        List<String> params = elasticArticleInfoServise.getIkAnalyzeSearchTerms("护士");
//
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery();
//        for (int i = 0; i < params.size(); i++) {
//            functionScoreQueryBuilder.add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("titile", params.get(i))),
//                        ScoreFunctionBuilders.weightFactorFunction(1000-i))
//                    .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("articleText", params.get(i))),
//                            ScoreFunctionBuilders.weightFactorFunction(100-i));
//        }
//
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(functionScoreQueryBuilder).build();
//
//        Iterable<ArticleInfo> searchResult = elasticArticleInfoRepository.search(searchQuery);
//        Iterator<ArticleInfo> iterator = searchResult.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next().getArticleText());
//        }
    }

    /**
      *function:全量更新es
     */
    @Test
    public void articleInfoElasticAdd(){
        elasticArticleInfoRepository.deleteAll();
        List<ArticleInfo> articleInfos = articleInfoDao.selectArticleInfos();
        for (int i = 0; i < articleInfos.size(); i++) {
            elasticArticleInfoRepository.save(articleInfos.get(i));
        }

    }


}
