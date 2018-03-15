package com.tangdou.succulent.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户相关
 * @author 木叶丸
 * @date 2018/3/15
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public String userList() {
        return "/user/list";
    }

}
