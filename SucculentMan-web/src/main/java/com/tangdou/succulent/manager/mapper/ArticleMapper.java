package com.tangdou.succulent.manager.mapper;


import com.tangdou.succulent.manager.api.article.Article;

import java.util.List;

/**
 * 文章相关
 * @author 木叶丸
 * @date 2018/3/18
 */
public interface ArticleMapper {

    /**
     * 插入一条文章信息
     * @param article 文章信息
     */
    void insert(Article article);

    /**
     * 条件查询文章列表信息
     * @param article 查询条件
     * @return 文章列表信息
     */
    List<Article> selectByList(Article article);

    /**
     * 通过id修改发布状态
     * @param article 状态信息
     */
    void updateStatusById(Article article);
}
