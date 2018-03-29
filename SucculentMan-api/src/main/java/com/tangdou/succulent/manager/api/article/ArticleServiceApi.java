package com.tangdou.succulent.manager.api.article;

import com.github.pagehelper.PageInfo;

/**
 * 前端文章相关接口
 * @author 木叶丸
 * @date 2018/3/29
 */
public interface ArticleServiceApi {

    /**
     * 分页查询文章信息
     * @param article 查询条件
     * @return 文章分页信息
     */
    PageInfo<Article> list(Article article);
}
