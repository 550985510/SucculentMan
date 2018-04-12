package com.tangdou.succulent.manager.api.user;

import com.tangdou.succulent.manager.api.user.model.User;

/**
 * 用户相关接口
 * @author 木叶丸
 * @date 2018/3/16
 */
public interface UserServiceApi {

    /**
     * 用户登陆
     * @param mobile 手机号
     * @param passWord 密码
     * @return 用户信息
     */
    User login(String mobile, String passWord);

    /**
     * 用户注册
     * @param mobile 手机号
     * @param passWord 密码
     */
    void register(String mobile, String passWord);
}
