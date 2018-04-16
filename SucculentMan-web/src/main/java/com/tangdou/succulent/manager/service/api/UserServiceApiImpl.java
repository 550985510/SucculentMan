package com.tangdou.succulent.manager.service.api;

import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
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
    public ResponseResult<User> login(String mobile, String passWord) {
        //查询用户是否存在，若存在获取该用户盐值
        User user = userMapper.selectByMobile(mobile);
        if (user == null) {
            return new ResponseResult<>(RestResultEnum.ERROR_LOGIN);
        } else {
            String salt = user.getSalt();
            //根据盐值和用户输入密码加密
            String passphrase = SecurityPasswordUtils.getPassphrase(salt, passWord);
            user.setPassWord(passphrase);
            User isLogin = userMapper.selectForLogin(user);
            if (isLogin == null) {
                return new ResponseResult<>(RestResultEnum.ERROR_LOGIN);
            }
            return new ResponseResult<>(userMapper.selectForLogin(user));
        }
    }

    /**
     * 用户注册
     *
     * @param mobile   手机号
     * @param passWord 密码
     * @param nickName 昵称
     * @return 操作状态
     */
    @Override
    public ResponseResult register(String mobile, String passWord, String nickName) {
        if (passWord.length() < 6 || passWord.length() > 32) {
            return new ResponseResult<>(RestResultEnum.ERROR_PASSWORD_LENGTH);
        }
        User user = userMapper.selectByMobile(mobile);
        if (user != null) {
            return new ResponseResult<>(RestResultEnum.ERROR_MOBILE_EXIST);
        } else if (userMapper.countByNickName(nickName) != 0){
            return new ResponseResult<>(RestResultEnum.ERROR_NICKNAME_EXIST);
        } else {
            user = new User();
            String salt = SecurityPasswordUtils.getSalt();
            String passphrase = SecurityPasswordUtils.getPassphrase(salt, passWord);
            user.setSalt(salt);
            user.setMobile(mobile);
            user.setNickName(nickName);
            user.setPassWord(passphrase);
            userMapper.insert(user);
            return new ResponseResult<>(RestResultEnum.SUCCESS);
        }
    }

    /**
     * 通过id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    public ResponseResult<User> findById(Integer id) {
        User user = userMapper.selectById(id);
        return new ResponseResult<>(user);
    }
}
