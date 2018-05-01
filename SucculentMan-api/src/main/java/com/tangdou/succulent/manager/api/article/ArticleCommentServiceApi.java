package com.tangdou.succulent.manager.api.article;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.model.ArticleComment;
import com.tangdou.succulent.manager.api.common.ResponseResult;

/**
 * 文章评论接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:34
 */
public interface ArticleCommentServiceApi {

    /**
     * 新增评论
     * @param articleComment 评论信息
     * @return 操作状态
     */
    ResponseResult add(ArticleComment articleComment);

    /**
     * 分页查询评论信息列表
     * @param articleComment 查询条件
     * @return 评论内容列表信息
     */
    ResponseResult<PageInfo<ArticleComment>> findPage(ArticleComment articleComment);
}
