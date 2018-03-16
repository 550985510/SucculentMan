package com.tangdou.succulent.manager.api.user;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.bean.common.ResponseResult;
import com.tangdou.succulent.manager.bean.user.User;
import com.tangdou.succulent.manager.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户相关
 * @author 木叶丸
 * @date 2018/3/15
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @PostMapping("/list")
    public ResponseResult<PageInfo<User>> findList(@RequestBody User user) {
        PageInfo<User> pageInfo = userService.findList(user);
        return new ResponseResult<>(pageInfo);
    }
}
