package com.nzxs2.controller;

import com.nzxs2.domin.ArticleInfo;
import com.nzxs2.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Ryan
 * @Date 2017/9/19 19:34
 * @Function
 */
@RestController
@RequestMapping("/article")
public class ArticleRestController {

    @Autowired
    private ArticleInfoService articleInfoService;
    @RequestMapping(value = "/getArticleListByPage",method = RequestMethod.POST)
    public List<ArticleInfo> getArticleListByPage(Integer pageNumber){
        List<ArticleInfo> articleInfos = articleInfoService.selectArticleInfos(1);
        return articleInfos;
    }
}
