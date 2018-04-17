package com.tangdou.succulent.manager.service.api;

import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.api.user.UserFollowServiceApi;
import com.tangdou.succulent.manager.api.user.model.UserFollow;
import com.tangdou.succulent.manager.mapper.UserFollowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户关注相关接口
 * @author 木叶丸
 * @date 2018/4/16
 */
@Service
public class UserFollowServiceApiImpl implements UserFollowServiceApi {

    @Resource
    private UserFollowMapper userFollowMapper;

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
}
