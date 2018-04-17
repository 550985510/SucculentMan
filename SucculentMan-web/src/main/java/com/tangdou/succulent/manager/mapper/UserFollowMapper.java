package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.user.model.UserFollow;

import java.util.List;

/**
 * 用户关注相关
 * @author 木叶丸
 * @date 2018/4/16
 */
public interface UserFollowMapper {

    /**
     * 新增关注信息
     * @param userFollow 关注信息
     */
    void insert(UserFollow userFollow);

    /**
     * 修改关注状态
     * @param userFollow 修改信息
     */
    void updateStatusById(UserFollow userFollow);

    /**
     * 通过双方id查询（判断是否建立过关注关系）
     * @param userFollow 关注信息
     * @return 关系信息
     */
    UserFollow selectByBothId(UserFollow userFollow);

    /**
     * 通过双方id统计（判断关注状态）
     * @param userFollow 关注信息
     * @return 统计数量
     */
    Integer countFollowedByBothId(UserFollow userFollow);

    /**
     * 通过用户id查询关系列表（查询关注列表）
     * @param userId 用户id
     * @return 关注关系列表信息
     */
    List<UserFollow> selectByUserId(Integer userId);

    /**
     * 通过被关注用户id查询关系列表（查询粉丝列表）
     * @param followedId 被关注用户id
     * @return 关注关系列表信息
     */
    List<UserFollow> selectByFollowedId(Integer followedId);

    /**
     * 通过用户id统计（查询用户关注人数）
     * @param userId 用户id
     * @return 统计数量
     */
    Integer countByUserId(Integer userId);

    /**
     * 通过被关注用户id统计（查询用户粉丝数量）
     * @param followedId 被关注用户id
     * @return 统计数量
     */
    Integer countByFollowedId(Integer followedId);
}
