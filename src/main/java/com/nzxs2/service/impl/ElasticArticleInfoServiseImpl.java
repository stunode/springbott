package com.nzxs2.service.impl;

import com.nzxs2.domin.ArticleInfo;
import com.nzxs2.repository.ElasticArticleInfoRepository;
import com.nzxs2.service.ElasticArticleInfoServise;
import org.apache.commons.collections.IteratorUtils;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ryan
 * @Date 2017/9/20 20:19
 * @Function
 */
@Service
public class ElasticArticleInfoServiseImpl implements ElasticArticleInfoServise {
    @Autowired
    private ElasticArticleInfoRepository elasticArticleInfoRepository;

    @Override
    public ArticleInfo queryArticleInfoById(Integer id) {
        return elasticArticleInfoRepository.findOne(id);
    }

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public ArticleInfo queryArticleInfoByTitle(String title) {
        return elasticArticleInfoRepository.findByTitle(title);
    }

    @Override
    public List<ArticleInfo> queryArticleByElastic(String queryString) {
        List<String> params = this.getIkAnalyzeSearchTerms(queryString);

        /**
          *function:es查询不分页,默认第一页显示500条
         */
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 500;
            }

            @Override
            public int getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        for (int i = 0; i < params.size(); i++) {
            queryBuilder.must(QueryBuilders.multiMatchQuery(params.get(i), "title", "articleText"));
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(queryBuilder).build();

        Iterable<ArticleInfo> searchResult = elasticArticleInfoRepository.search(searchQuery);
        List<ArticleInfo> result = new ArrayList<>();
        searchResult.forEach(result::add);
        return result;
    }

    /**
     * 调用 ES 获取 IK 分词后结果
     *
     * @param searchContent
     * @return
     */
    private List<String> getIkAnalyzeSearchTerms(String searchContent) {
        // 调用 IK 分词分词
        AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(elasticsearchTemplate.getClient(),
                AnalyzeAction.INSTANCE, "nzxs", searchContent);
        ikRequest.setTokenizer("ik");
        List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();

        // 循环赋值
        List<String> searchTermList = new ArrayList<>();
        ikTokenList.forEach(ikToken -> {
            searchTermList.add(ikToken.getTerm());
        });

        return searchTermList;
    }

}
