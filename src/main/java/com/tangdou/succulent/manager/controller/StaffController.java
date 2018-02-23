package com.tangdou.succulent.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 木叶丸
 * @date 2018/2/23
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/user")
    public String settings() {
        return "/staff/user";
    }
}
