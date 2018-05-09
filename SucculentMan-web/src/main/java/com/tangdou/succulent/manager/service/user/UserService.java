package com.tangdou.succulent.manager.service.user;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.user.model.User;

import java.util.Date;

/**
 * 用户相关
 * @author 木叶丸
 * @date 2018/3/15
 */
public interface UserService {

    /**
     * 分页查询用户列表信息
     * @param user 查询条件
     * @return 用户列表信息
     */
    PageInfo<User> findList(User user);

    /**
     * 统计注册用户时间
     *
     * @param dateRangeStart
     * @param dateRangeEnd
     * @return
     */
    Integer countByDateRange(Date dateRangeStart, Date dateRangeEnd);
}
