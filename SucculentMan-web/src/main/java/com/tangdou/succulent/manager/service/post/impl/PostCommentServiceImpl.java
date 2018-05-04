package com.tangdou.succulent.manager.service.post.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.post.model.PostComment;
import com.tangdou.succulent.manager.mapper.PostCommentMapper;
import com.tangdou.succulent.manager.mapper.PostMapper;
import com.tangdou.succulent.manager.mapper.UserMapper;
import com.tangdou.succulent.manager.service.post.PostCommentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 13:29
 */
@Service
public class PostCommentServiceImpl implements PostCommentService {

    @Resource
    private PostCommentMapper postCommentMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PostMapper postMapper;

    /**
     * 分页查询文章评论列表信息
     *
     * @param postComment 查询条件
     * @return 文章评论列表信息
     */
    @Override
    public PageInfo<PostComment> findByList(PostComment postComment) {
        PageHelper.startPage(postComment.getPage(), postComment.getPageSize());
        List<PostComment> list = postCommentMapper.selectList(postComment);
        for (PostComment item : list) {
            item.setUserAvatar(userMapper.selectById(item.getUserId()).getAvatar());
            item.setUserNickName(userMapper.selectById(item.getUserId()).getNickName());
            item.setPostTitle(postMapper.selectById(item.getPostId(),null).getTitle());
        }
        return new PageInfo<>(list);
    }

    /**
     * 逻辑删除评论信息
     *
     * @param postComment 评论信息
     */
    @Override
    public void delete(PostComment postComment) {
        postCommentMapper.updateById(postComment);
    }
}
