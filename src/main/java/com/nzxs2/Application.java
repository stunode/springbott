package com.nzxs2;

import com.nzxs2.dao.ArticleDao;
import com.nzxs2.domin.Article;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Boot 应用启动类
 */
@SpringBootApplication
@MapperScan("com.nzxs2.dao")
public class Application {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    CommandLineRunner demo(ArticleDao articleDao) {
//        return args -> {
//
//            List<Article> articles = Arrays.asList(new Article("zsadasd","http:dsadsadadsdasd"),new Article("zsadasdsa","http:dsadsadadsdasd"));
//            articles.forEach(article -> {
//                articleDao.insert(article);
//                System.err.println("插入数据:" + article.toString());
//            });
//        };
//    }
}