package com.tangdou.succulent.manager.api.user;

import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.user.model.User;
import org.springframework.web.bind.annotation.RequestBody;

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
    ResponseResult<User> login(String mobile, String passWord);

    /**
     * 用户注册
     * @param mobile 手机号
     * @param passWord 密码
     * @param nickName 昵称
     * @return 操作状态
     */
    ResponseResult register(String mobile, String passWord, String nickName);

    /**
     * 通过id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    ResponseResult<User> findById(Integer id);

    /**
     * 修改用户基本信息
     * @param user 修改内容
     * @return 操作状态
     */
    ResponseResult edit(User user);

    /**
     * 修改用户密码
     * @param id 用户id
     * @param passWord 原密码
     * @param newPassWord 新密码
     * @return 操作状态
     */
    ResponseResult editPassWord(Integer id, String passWord, String newPassWord);
}
