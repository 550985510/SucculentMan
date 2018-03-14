package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.bean.user.User;

/**
 * @author 木叶丸
 * @date 2018/3/14
 */
public interface UserMapper {

    /**
     * 新增用户
     * @param user 用户信息
     */
    void insert(User user);
}
