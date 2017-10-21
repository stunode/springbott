package com.nzxs2.controller;

import com.nzxs2.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Ryan
 * @Date 2017/9/15 23:35
 * @Function
 */

@Controller
@RequestMapping("/ajax")
public class AjaxTestController {


    @Autowired
    private ArticleInfoService articleInfoService;
    @RequestMapping("/test")
    public String test() {

        return "ajaxtest";
    }

    @RequestMapping("/test1")
    @ResponseBody
    public  String test1() {

        return "ajaxtest";
    }
}
