package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.article.model.ArticleContent;

/**
 * 文章内容相关
 * @author 木叶丸
 * @date 2018/3/18
 */
public interface ArticleContentMapper {

    /**
     * 插入一条文章内容数据
     * @param articleContent 文章内容信息
     */
    void insert(ArticleContent articleContent);

    /**
     * 通过文章id查询文章内容
     * @param articleId 文章编号
     * @return 文章内容信息
     */
    ArticleContent selectByArticleId(Integer articleId);

    /**
     * 通过文章id修改文章内容
     * @param content 文章内容信息
     */
    void updateByArticleId(ArticleContent content);
}
