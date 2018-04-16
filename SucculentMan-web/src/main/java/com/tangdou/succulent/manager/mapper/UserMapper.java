package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.user.model.User;
import java.util.List;

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

    /**
     * 查询用户列表
     * @param user 查询条件
     * @return 用户列表信息
     */
    List<User> selectList(User user);

    /**
     * 通过手机号(账号)查询用户
     * @param mobile 手机号
     * @return 用户信息
     */
    User selectByMobile(String mobile);

    /**
     * 登陆
     * @param user 账号密码
     * @return 用户信息
     */
    User selectForLogin(User user);

    /**
     * 通过id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    User selectById(Integer id);

    /**
     * 通过昵称统计用户信息数量
     * @param nickName 昵称
     * @return 用户数量
     */
    Integer countByNickName(String nickName);
}
