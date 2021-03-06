package com.tangdou.succulent.manager.api.user;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.user.model.UserFollow;

/**
 * 用户关注相关接口
 * @author 木叶丸
 * @date 2018/4/16
 */
public interface UserFollowServiceApi {

    /**
     * 用户关注
     * @param userId 用户id
     * @param followedId 被关注用户id
     * @return 响应状态
     */
    ResponseResult follow(Integer userId, Integer followedId);

    /**
     * 取消关注
     * @param userId 用户id
     * @param followedId 被关注用户id
     * @return 响应状态
     */
    ResponseResult unFollow(Integer userId, Integer followedId);

    /**
     * 判断是否关注
     * @param userId 用户id
     * @param followedId 被关注用户id
     * @return 响应状态
     */
    ResponseResult<Boolean> isFollowed(Integer userId, Integer followedId);

    /**
     * 查询用户关注人数
     * @param userId 用户id
     * @return 用户关注人数
     */
    ResponseResult<Integer> findUserFollowedNum(Integer userId);

    /**
     * 查询用户粉丝数量
     * @param followedId 被关注用户id
     * @return 用户粉丝数量
     */
    ResponseResult<Integer> findUserFollowerNum(Integer followedId);

    /**
     * 分页查询用户关注列表
     * @param userFollow 查询条件
     * @return 用户列表信息
     */
    ResponseResult<PageInfo<UserFollow>> findFollowedList(UserFollow userFollow);

    /**
     * 分页查询用户粉丝列表
     * @param userFollow 查询条件
     * @return 用户列表信息
     */
    ResponseResult<PageInfo<UserFollow>> findFollowerList(UserFollow userFollow);
}
