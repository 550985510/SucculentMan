package com.tangdou.succulent.manager.service.post;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.post.model.PostComment;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 13:29
 */
public interface PostCommentService {

    /**
     * 分页查询帖子回帖列表信息
     * @param postComment 查询条件
     * @return 帖子回帖列表信息
     */
    PageInfo<PostComment> findByList(PostComment postComment);

    /**
     * 逻辑删除回帖信息
     * @param postComment 回帖信息
     */
    void delete(PostComment postComment);
}
