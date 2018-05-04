package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.post.model.PostComment;

import java.util.List;

/**
 * 帖子评论
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/2 16:56
 */
public interface PostCommentMapper {

    /**
     * 新增评论
     * @param postComment 评论内容
     */
    void insert(PostComment postComment);

    /**
     * 修改评论内容（删除状态）
     * @param postComment 修改内容
     */
    void updateById(PostComment postComment);

    /**
     * 通过主键id查询评论
     * @param id 主键id
     * @return 评论信息
     */
    PostComment selectById(Integer id);

    /**
     * 查询评论列表信息
     * @param postComment 查询条件
     * @return 评论列表信息
     */
    List<PostComment> selectList(PostComment postComment);

    /**
     * 统计帖子评论数量
     * @param postId 帖子id
     * @return 帖子评论数量
     */
    Integer countByPostId(Integer postId);
}
