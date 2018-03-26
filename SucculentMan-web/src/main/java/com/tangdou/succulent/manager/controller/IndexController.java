package com.tangdou.succulent.manager.controller;

import com.tangdou.succulent.manager.config.AdminSecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author 木叶丸
 * @date 2017/12/12
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String root(){
        return "index";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(AdminSecurityConfig.SESSION_KEY) != null) {
            return "index";
        }
        return "/login/login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/error_404")
    public String notFoundError(){
        return "404";
    }

    @GetMapping("/error_500")
    public String serverError(){
        return "500";
    }

    @GetMapping("/error_403")
    public String forbiddenError(){
        return "403";
    }

}
