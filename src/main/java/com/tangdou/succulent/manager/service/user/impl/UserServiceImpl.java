package com.tangdou.succulent.manager.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.bean.staff.StaffUser;
import com.tangdou.succulent.manager.bean.user.User;
import com.tangdou.succulent.manager.mapper.UserMapper;
import com.tangdou.succulent.manager.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户相关
 * @author 木叶丸
 * @date 2018/3/15
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

   /**
     * 分页查询用户列表信息
     *
     * @param user 查询条件
     * @return 用户列表信息
     */
    @Override
    public PageInfo<User> findList(User user) {
        PageHelper.startPage(user.getPage(), user.getPageSize());
        List<User> list = userMapper.selectList(user);
        return new PageInfo<>(list);
    }
}
