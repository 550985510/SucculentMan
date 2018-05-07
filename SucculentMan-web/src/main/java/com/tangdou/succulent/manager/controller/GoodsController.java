package com.tangdou.succulent.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品相关
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 20:22
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @GetMapping("/list")
    public String list() {
        return "/goods/list";
    }

    @GetMapping("/comment/list")
    public String commentList() {
        return "/goods/commentList";
    }
}
