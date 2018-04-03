package com.tangdou.succulent.manager.service.api;

import com.tangdou.succulent.manager.api.user.UserServiceApi;
import com.tangdou.succulent.manager.api.user.model.User;
import com.tangdou.succulent.manager.mapper.UserMapper;
import com.tangdou.succulent.manager.util.SecurityPasswordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户相关
 * @author 木叶丸
 * @date 2018/3/16
 */
@Service
public class UserServiceApiImpl implements UserServiceApi {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户登陆
     * @param mobile 手机号
     * @param passWord 密码
     * @return 用户信息
     */
    @Override
    public User login(String mobile, String passWord) {
        //查询用户是否存在，若存在获取该用户盐值
        User user = userMapper.selectByMobile(mobile);
        if (user == null) {
            return null;
        } else {
            String salt = user.getSalt();
            //根据盐值和用户输入密码加密
            String passphrase = SecurityPasswordUtils.getPassphrase(salt, passWord);
            user.setPassWord(passphrase);
            return userMapper.selectForLogin(user);
        }
    }
}
