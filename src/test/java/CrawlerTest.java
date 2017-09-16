import com.nzxs2.Application;
import com.nzxs2.dao.ArticleDao;
import com.nzxs2.dao.ArticleInfoDao;
import com.nzxs2.dao.PageDao;
import com.nzxs2.domin.Article;
import com.nzxs2.domin.ArticleInfo;
import com.nzxs2.domin.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
    }

    @Test
    public void articleDetail() {
        ArticleInfo a = articleInfoDao.selectArticleDetail(32);

    }
}
