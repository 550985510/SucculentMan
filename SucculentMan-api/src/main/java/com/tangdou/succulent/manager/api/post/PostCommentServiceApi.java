package com.tangdou.succulent.manager.api.post;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.post.model.PostComment;
import com.tangdou.succulent.manager.api.common.ResponseResult;

/**
 * 帖子回帖接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:34
 */
public interface PostCommentServiceApi {

    /**
     * 新增回帖
     * @param postComment 回帖信息
     * @return 操作状态
     */
    ResponseResult add(PostComment postComment);

    /**
     * 分页查询回帖信息列表
     * @param postComment 查询条件
     * @return 回帖内容列表信息
     */
    ResponseResult<PageInfo<PostComment>> findPage(PostComment postComment);
}
