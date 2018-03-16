package com.tangdou.succulent.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 木叶丸
 * @date 2018/3/16
 */
@Controller
@RequestMapping("/module")
public class ModuleController {

    @GetMapping("/list")
    public String list() {
        return "/module/list";
    }

}
