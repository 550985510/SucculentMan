package com.tangdou.succulent.manager.api.article;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.model.ArticleCollection;
import com.tangdou.succulent.manager.api.common.ResponseResult;

/**
 * 文章收藏相关接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/25 16:30
 */
public interface ArticleCollectionServiceApi {

    /**
     * 用户收藏
     * @param userId 用户id
     * @param articleId 文章id
     * @return 响应状态
     */
    ResponseResult collection(Integer userId, Integer articleId);

    /**
     * 取消收藏
     * @param userId 用户id
     * @param articleId 文章id
     * @return 响应状态
     */
    ResponseResult unCollection(Integer userId, Integer articleId);

    /**
     * 判断是否收藏
     * @param userId 用户id
     * @param articleId 文章id
     * @return 响应状态
     */
    ResponseResult<Boolean> isCollected(Integer userId, Integer articleId);

    /**
     * 查询文章收藏数量
     * @param articleId 文章id
     * @return 用户粉丝数量
     */
    ResponseResult<Integer> findCollectedNum(Integer articleId);

    /**
     * 查询用户收藏列表
     * @param articleCollection 查询条件
     * @return 收藏列表信息
     */
    ResponseResult<PageInfo<ArticleCollection>> findList(ArticleCollection articleCollection);
}
