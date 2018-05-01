package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.article.model.ArticleComment;

import java.util.List;

/**
 * 文章评论
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:37
 */
public interface ArticleCommentMapper {

    /**
     * 新增评论
     * @param articleComment 评论内容
     */
    void insert(ArticleComment articleComment);

    /**
     * 修改评论内容（删除状态）
     * @param articleComment 修改内容
     */
    void updateById(ArticleComment articleComment);

    /**
     * 通过主键id查询评论
     * @param id 主键id
     * @return 评论信息
     */
    ArticleComment selectById(Integer id);

    /**
     * 查询评论列表信息
     * @param articleComment 查询条件
     * @return 评论列表信息
     */
    List<ArticleComment> selectList(ArticleComment articleComment);
}
