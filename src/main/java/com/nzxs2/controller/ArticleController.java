package com.nzxs2.controller;

import com.nzxs2.dao.ArticleInfoDao;
import com.nzxs2.domin.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Ryan
 * @Date 2017/9/15 23:35
 * @Function
 */

@Controller
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleInfoDao articleInfoDao;
    @RequestMapping("/details")
    public String details(@RequestParam Integer articleId,Model model) {

        ArticleInfo articleInfo = articleInfoDao.selectArticleDetail(articleId);
        model.addAttribute("articleDetails", articleInfo);
        return "detail";
    }

}
