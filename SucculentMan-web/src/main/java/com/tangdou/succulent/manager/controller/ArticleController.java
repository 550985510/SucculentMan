package com.tangdou.succulent.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文章相关
 * @author 木叶丸
 * @date 2018/3/19
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public String list() {
        return "/article/list";
    }
}
