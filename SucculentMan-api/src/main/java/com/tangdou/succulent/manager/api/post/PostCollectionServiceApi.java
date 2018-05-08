package com.tangdou.succulent.manager.api.post;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.model.ArticleCollection;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.post.model.PostCollection;

/**
 * 帖子收藏相关接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/25 16:30
 */
public interface PostCollectionServiceApi {

    /**
     * 用户收藏
     * @param userId 用户id
     * @param postId 帖子id
     * @return 响应状态
     */
    ResponseResult collection(Integer userId, Integer postId);

    /**
     * 取消收藏
     * @param userId 用户id
     * @param postId 帖子id
     * @return 响应状态
     */
    ResponseResult unCollection(Integer userId, Integer postId);

    /**
     * 判断是否收藏
     * @param userId 用户id
     * @param postId 帖子id
     * @return 响应状态
     */
    ResponseResult<Boolean> isCollected(Integer userId, Integer postId);

    /**
     * 查询帖子收藏数量
     * @param postId 帖子id
     * @return 用户粉丝数量
     */
    ResponseResult<Integer> findCollectedNum(Integer postId);

    /**
     * 查询用户收藏列表
     * @param postCollection 查询条件
     * @return 收藏列表信息
     */
    ResponseResult<PageInfo<PostCollection>> findList(PostCollection postCollection);
}
