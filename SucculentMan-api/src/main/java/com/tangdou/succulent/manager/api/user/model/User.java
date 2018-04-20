package com.tangdou.succulent.manager.api.user.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author 木叶丸
 * @date 2018/3/14
 */
@Data
public class User implements Serializable{

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 封面背景图
     */
    private String background;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 混合加密盐值
     */
    private String salt;

    /**
     * 是否可以登录 0:可以 1:不可以
     */
    private Integer enabled;

    /**
     * 是否被锁定 0:未锁定 1:锁定
     */
    private Integer locked;

    /**
     * 被锁定时间
     */
    private Date lockedDate;

    /**
     * 最后登陆日期
     */
    private Date loginDate;

    /**
     * 登陆错误次数
     */
    private Integer loginFailureCount;

    /**
     * 最后登录ip
     */
    private String loginIp;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer pageSize;
}
