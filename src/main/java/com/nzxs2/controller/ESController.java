package com.nzxs2.controller;

import com.nzxs2.domin.ArticleInfo;
import com.nzxs2.repository.ElasticArticleInfoRepository;
import com.nzxs2.service.ElasticArticleInfoServise;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ryan
 * @Date 2017/9/20 20:21
 * @Function
 */
@Controller
public class ESController {
    @Autowired
    private ElasticArticleInfoServise elasticArticleInfoServise;
    @Autowired
    private ElasticArticleInfoRepository elasticArticleInfoRepository;


    @RequestMapping("/esArticleInfoQuery")
    public String queryArticleInfoByTitle(@RequestParam String queryParam, ModelMap modelMap){


        List<ArticleInfo> articleInfos = elasticArticleInfoServise.queryArticleByElastic(queryParam);
        modelMap.addAttribute("articleInfos",articleInfos);
        return "esQuery";
    }
}
