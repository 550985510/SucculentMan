package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.article.model.ArticleCollection;

import java.util.List;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/25 16:31
 */
public interface ArticleCollectionMapper {

    /**
     * 新增收藏信息
     * @param articleCollection 收藏信息
     */
    void insert(ArticleCollection articleCollection);

    /**
     * 修改收藏状态
     * @param articleCollection 修改信息
     */
    void updateStatusById(ArticleCollection articleCollection);

    /**
     * 通过双方id查询（判断是否建立过收藏关系）
     * @param articleCollection 收藏信息
     * @return 关系信息
     */
    ArticleCollection selectByBothId(ArticleCollection articleCollection);

    /**
     * 通过双方id统计（判断收藏状态）
     * @param articleCollection 收藏信息
     * @return 统计数量
     */
    Integer countByBothId(ArticleCollection articleCollection);

    /**
     * 通过用户id查询关系列表（查询收藏列表）
     * @param userId 用户id
     * @return 收藏关系列表信息
     */
    List<ArticleCollection> selectByUserId(Integer userId);

    /**
     * 通过被收藏用户id统计（查询文章收藏数量）
     * @param articleId 文章id
     * @return 统计数量
     */
    Integer countByArticleId(Integer articleId);
}
