package com.tangdou.succulent.manager.api.post;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.post.model.Post;
import com.tangdou.succulent.manager.api.common.ResponseResult;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/2 11:33
 */
public interface PostServiceApi {

    /**
     * 分页查询帖子信息
     * @param post 查询条件
     * @return 帖子分页信息
     */
    ResponseResult<PageInfo<Post>> list(Post post);

    /**
     * 帖子详情
     * @param postId 帖子id
     * @param deleted 帖子发布状态
     * @return 帖子信息
     */
    ResponseResult<Post> detail(Integer postId, Integer deleted);

    /**
     * 发表新帖
     * @param post 帖子内容
     * @return 操作状态
     */
    ResponseResult add(Post post);
}
