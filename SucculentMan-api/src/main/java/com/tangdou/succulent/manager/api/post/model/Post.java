package com.tangdou.succulent.manager.api.post.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/2 11:33
 */
@Data
public class Post implements Serializable {

    /**
     * 帖子编号
     */
    private Integer id;

    /**
     * 帖子主题
     */
    private String title;

    /**
     * 所属模块编号
     */
    private Integer moduleId;

    /**
     * 所属模块名称(通过模块编号查到)
     */
    private String moduleName;

    /**
     * 逻辑删除状态 0:未删除 1:已删除
     */
    private Integer deleted;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 收藏数量
     */
    private Integer collectedNum;

    /**
     * 作者（用户编号）
     */
    private Integer userId;

    /**
     * 作者姓名
     */
    private String userNickName;

    /**
     * 作者头像
     */
    private String userAvatar;

    /**
     * 帖子内容
     */
    private String content;

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
}
