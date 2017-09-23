package com.nzxs2.controller;

import com.nzxs2.dao.ArticleDao;
import com.nzxs2.dao.ArticleInfoDao;
import com.nzxs2.domin.ArticleInfo;
import com.nzxs2.domin.Resources;
import com.nzxs2.service.ArticleInfoService;
import com.nzxs2.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登录
 *
 * @author
 * @create 2017-07-03 13:58
 */

@Controller
public class LoginController {

    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private ArticleInfoService articleInfoService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/index")
    public String index(Model model) {
        return "index";
    }


    @RequestMapping("/login")
    public String login() {
        //todo 登录页面
        return null;
    }
}
