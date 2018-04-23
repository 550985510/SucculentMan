package com.tangdou.succulent.manager.api.article;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
     * 所属模块名称(通过模块编号查到)
     */
    private String moduleName;

    /**
     * 发布状态 0:待审核 1:未通过 2:已发布 3:已下架
     */
    private Integer status;

    /**
     * 是否首页显示 0:不显示 1:显示
     */
    private Integer bannerStatus;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 关键词
     */
    private List<String> keywordList;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 作者（员工编号）
     */
    private Integer staffId;

    /**
     * 作者姓名
     */
    private String author;

    /**
     * 文章内容
     */
    private String content;

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

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer pageSize;
}
