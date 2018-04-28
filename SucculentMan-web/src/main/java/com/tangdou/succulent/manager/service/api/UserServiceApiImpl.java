package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.api.user.UserServiceApi;
import com.tangdou.succulent.manager.api.user.model.User;
import com.tangdou.succulent.manager.mapper.UserFollowMapper;
import com.tangdou.succulent.manager.mapper.UserMapper;
import com.tangdou.succulent.manager.util.SecurityPasswordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户相关
 * @author 木叶丸
 * @date 2018/3/16
 */
@Service
public class UserServiceApiImpl implements UserServiceApi {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserFollowMapper userFollowMapper;

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

    /**
     * 修改用户基本信息
     *
     * @param user 修改内容
     * @return 操作状态
     */
    @Override
    public ResponseResult edit(User user) {
        userMapper.update(user);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 修改用户密码
     *
     * @param id          用户id
     * @param passWord    原密码
     * @param newPassWord 新密码
     * @return 操作状态
     */
    @Override
    public ResponseResult editPassWord(Integer id, String passWord, String newPassWord) {
        User user = userMapper.selectById(id);
        String passphrase = SecurityPasswordUtils.getPassphrase(user.getSalt(), passWord);
        if (!passphrase.equals(user.getPassWord())) {
            return new ResponseResult(RestResultEnum.ERROR_PASSWORD);
        } else if (newPassWord.length() < 6 || newPassWord.length() > 32) {
            return new ResponseResult<>(RestResultEnum.ERROR_PASSWORD_LENGTH);
        } else {
            User info = new User();
            String salt = SecurityPasswordUtils.getSalt();
            String newPassphrase = SecurityPasswordUtils.getPassphrase(salt, newPassWord);
            info.setId(id);
            info.setSalt(salt);
            info.setPassWord(newPassphrase);
            userMapper.update(info);
            return new ResponseResult(RestResultEnum.SUCCESS);
        }
    }

    /**
     * 随机查出五个用户首页展示
     *
     * @return 用户列表信息
     */
    @Override
    public ResponseResult<List<User>> showUsers() {
        List<User> list = userMapper.selectRandom();
        for (User user : list) {
            //用户关注人数
            user.setFollowedNum(userFollowMapper.countByUserId(user.getId()));
            //用户粉丝数量
            user.setFollowerNum(userFollowMapper.countByFollowedId(user.getId()));
            //用户个人信息不显示
            user.setName(null);
            user.setPassWord(null);
            user.setSalt(null);
            user.setMobile(null);
            user.setEmail(null);
            user.setEnabled(null);
            user.setCreatedTime(null);
            user.setModifiedTime(null);
            user.setLocked(null);
            user.setLoginFailureCount(null);
        }
        return new ResponseResult<>(list);
    }

    /**
     * 分页查询所有用户
     *
     * @return 用户列表信息
     */
    @Override
    public ResponseResult<PageInfo<User>> findAllUsers(User user) {
        PageHelper.startPage(user.getPage(), user.getPageSize());
        List<User> list = userMapper.selectList(user);
        for (User item : list) {
            //用户关注人数
            item.setFollowedNum(userFollowMapper.countByUserId(item.getId()));
            //用户粉丝数量
            item.setFollowerNum(userFollowMapper.countByFollowedId(item.getId()));
            //用户个人信息不显示
            item.setName(null);
            item.setPassWord(null);
            item.setSalt(null);
            item.setMobile(null);
            item.setEmail(null);
            item.setEnabled(null);
            item.setCreatedTime(null);
            item.setModifiedTime(null);
            item.setLocked(null);
            item.setLoginFailureCount(null);
        }
        return new ResponseResult<>(new PageInfo<>(list));
    }
}
