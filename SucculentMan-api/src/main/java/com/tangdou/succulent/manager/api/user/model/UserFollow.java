package com.tangdou.succulent.manager.api.user.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户关注关系
 * @author 木叶丸
 * @date 2018/4/16
 */
@Data
public class UserFollow implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 被关注用户id
     */
    private Integer followedId;

    /**
     * 关注状态 0:关注 1:取消关注
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 关注人数
     */
    private Integer followedNum;

    /**
     * 粉丝人数
     */
    private Integer followerNum;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像url
     */
    private String avatar;
}
