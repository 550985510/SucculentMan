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
     * 查询员工列表
     * @param user 查询条件
     * @return 员工列表信息
     */
    List<User> selectList(User user);
}
