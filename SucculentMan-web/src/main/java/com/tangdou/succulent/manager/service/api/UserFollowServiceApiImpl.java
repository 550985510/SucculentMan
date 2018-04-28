package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.api.user.UserFollowServiceApi;
import com.tangdou.succulent.manager.api.user.model.User;
import com.tangdou.succulent.manager.api.user.model.UserFollow;
import com.tangdou.succulent.manager.mapper.UserFollowMapper;
import com.tangdou.succulent.manager.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户关注相关接口
 * @author 木叶丸
 * @date 2018/4/16
 */
@Service
public class UserFollowServiceApiImpl implements UserFollowServiceApi {

    @Resource
    private UserFollowMapper userFollowMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 用户关注
     *
     * @param userId     用户id
     * @param followedId 被关注用户id
     * @return 响应状态
     */
    @Override
    public ResponseResult follow(Integer userId, Integer followedId) {
        UserFollow info = new UserFollow();
        info.setUserId(userId);
        info.setFollowedId(followedId);
        UserFollow userFollow = userFollowMapper.selectByBothId(info);
        if (userFollow == null) {
            userFollowMapper.insert(info);
        } else {
            userFollow.setStatus(0);
            userFollowMapper.updateStatusById(userFollow);
        }
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 取消关注
     *
     * @param userId     用户id
     * @param followedId 被关注用户id
     * @return 响应状态
     */
    @Override
    public ResponseResult unFollow(Integer userId, Integer followedId) {
        UserFollow info = new UserFollow();
        info.setUserId(userId);
        info.setFollowedId(followedId);
        UserFollow userFollow = userFollowMapper.selectByBothId(info);
        userFollow.setStatus(1);
        userFollowMapper.updateStatusById(userFollow);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 判断是否关注
     *
     * @param userId     用户id
     * @param followedId 被关注用户id
     * @return 响应状态
     */
    @Override
    public ResponseResult<Boolean> isFollowed(Integer userId, Integer followedId) {
        Boolean isFollowed = false;
        UserFollow info = new UserFollow();
        info.setUserId(userId);
        info.setFollowedId(followedId);
        if (userFollowMapper.countFollowedByBothId(info) != 0) {
            isFollowed = true;
        }
        return new ResponseResult<>(isFollowed);
    }

    /**
     * 查询用户关注人数
     *
     * @param userId 用户id
     * @return 用户关注人数
     */
    @Override
    public ResponseResult<Integer> findUserFollowedNum(Integer userId) {
        return new ResponseResult<>(userFollowMapper.countByUserId(userId));
    }

    /**
     * 查询用户粉丝数量
     *
     * @param followedId 被关注用户id
     * @return 用户粉丝数量
     */
    @Override
    public ResponseResult<Integer> findUserFollowerNum(Integer followedId) {
        return new ResponseResult<>(userFollowMapper.countByFollowedId(followedId));
    }

    /**
     * 分页查询用户关注列表
     *
     * @param userFollow 查询条件
     * @return 用户列表信息
     */
    @Override
    public ResponseResult<PageInfo<UserFollow>> findFollowedList(UserFollow userFollow) {
        PageHelper.startPage(userFollow.getPage(), userFollow.getPageSize());
        List<UserFollow> list = userFollowMapper.selectByUserId(userFollow.getUserId());
        for (UserFollow item : list) {
            User user = userMapper.selectById(item.getFollowedId());
            item.setAvatar(user.getAvatar());
            item.setNickName(user.getNickName());
            //用户关注人数
            item.setFollowedNum(userFollowMapper.countByUserId(item.getFollowedId()));
            //用户粉丝数量
            item.setFollowerNum(userFollowMapper.countByFollowedId(item.getFollowedId()));
            //用户个人信息不显示
            item.setCreatedTime(null);
            item.setStatus(null);
        }
        return new ResponseResult<>(new PageInfo<>(list));
    }

    /**
     * 分页查询用户粉丝列表
     *
     * @param userFollow 查询条件
     * @return 用户列表信息
     */
    @Override
    public ResponseResult<PageInfo<UserFollow>> findFollowerList(UserFollow userFollow) {
        PageHelper.startPage(userFollow.getPage(), userFollow.getPageSize());
        List<UserFollow> list = userFollowMapper.selectByFollowedId(userFollow.getUserId());
        for (UserFollow item : list) {
            User user = userMapper.selectById(item.getUserId());
            item.setAvatar(user.getAvatar());
            item.setNickName(user.getNickName());
            //用户关注人数
            item.setFollowedNum(userFollowMapper.countByUserId(item.getUserId()));
            //用户粉丝数量
            item.setFollowerNum(userFollowMapper.countByFollowedId(item.getUserId()));
            //用户个人信息不显示
            item.setCreatedTime(null);
            item.setStatus(null);
        }
        return new ResponseResult<>(new PageInfo<>(list));
    }
}
