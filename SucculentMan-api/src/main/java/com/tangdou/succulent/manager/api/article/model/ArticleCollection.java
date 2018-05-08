package com.tangdou.succulent.manager.api.article.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章收藏关系
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/25 16:25
 */
@Data
public class ArticleCollection implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 关注状态 0:收藏 1:取消收藏
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
     * 文章信息
     */
    private Article article;

}
