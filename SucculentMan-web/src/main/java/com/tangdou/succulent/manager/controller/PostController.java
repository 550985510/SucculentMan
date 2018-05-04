package com.tangdou.succulent.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 贴子相关
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/2 16:53
 */
@Controller
@RequestMapping("/post")
public class PostController {

    @GetMapping("/list")
    public String list() {
        return "/post/list";
    }

    @GetMapping("/comment/list")
    public String commentList() {
        return "/post/commentList";
    }
}
