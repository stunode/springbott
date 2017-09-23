package com.nzxs2.repository;

import com.nzxs2.domin.ArticleInfo;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Author Ryan
 * @Date 2017/9/20 20:13
 * @Function
 */
@Component("elasticArticleInfoRepository")
public interface ElasticArticleInfoRepository extends ElasticsearchRepository<ArticleInfo, Integer> {
    ArticleInfo findByTitle(String title);
}
