package com.tangdou.succulent.manager.api.article.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章内容
 * @author 木叶丸
 * @date 2018/3/18
 */
@Data
public class ArticleContent implements Serializable {

    /**
     * 文章内容编号
     */
    private Integer id;

    /**
     * 文章编号
     */
    private Integer articleId;

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
}
