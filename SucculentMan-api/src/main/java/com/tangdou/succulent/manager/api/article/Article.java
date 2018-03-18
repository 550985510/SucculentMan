package com.tangdou.succulent.manager.api.article;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 * @author 木叶丸
 * @date 2018/3/18
 */
@Data
public class Article implements Serializable {

    /**
     * 文章编号
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 首页显示图片
     */
    private String img;

    /**
     * 所属模块编号
     */
    private Integer moduleId;

    /**
     * 发布状态 0:待审核 1:未通过 2:已发布 3:已下架
     */
    private Integer status;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 作者（员工编号）
     */
    private Integer staffId;

    /**
     * 文章内容
     */
    private ArticleContent content;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 最后修改人
     */
    private String modifiedBy;
}
