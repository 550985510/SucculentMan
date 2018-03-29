package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.Article;
import com.tangdou.succulent.manager.api.article.ArticleServiceApi;
import com.tangdou.succulent.manager.service.article.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 木叶丸
 * @date 2018/3/29
 */
@Service
public class ArticleServiceApiImpl implements ArticleServiceApi {

    @Resource
    private ArticleService articleService;

    /**
     * 分页查询文章信息
     *
     * @param article 查询条件
     * @return 文章分页信息
     */
    @Override
    public PageInfo<Article> list(Article article) {
        PageInfo<Article> list = articleService.findByList(article);
        return list;
    }
}
