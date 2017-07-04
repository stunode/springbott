package com.damuzhi.controller;

import com.damuzhi.domin.Resources;
import com.damuzhi.service.ResourcesService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登录
 *
 * @author
 * @create 2017-07-03 13:58
 */

@RestController
public class LoginController {

    @Autowired
    private ResourcesService resourcesService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("sb", "this is my fries测试不");
        return "index";
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public List<Resources> findOneCity() {
        return resourcesService.queryAll();
    }

    @RequestMapping("/login")
    public String login() {
        //todo 登录页面
        return null;
    }
}
