package com.tangdou.succulent.manager.service.article;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.model.ArticleComment;

/**
 * 文章评论
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:36
 */
public interface ArticleCommentService {

    /**
     * 分页查询文章评论列表信息
     * @param articleComment 查询条件
     * @return 文章评论列表信息
     */
    PageInfo<ArticleComment> findByList(ArticleComment articleComment);

    /**
     * 逻辑删除评论信息
     * @param articleComment 评论信息
     */
    void delete(ArticleComment articleComment);
}
