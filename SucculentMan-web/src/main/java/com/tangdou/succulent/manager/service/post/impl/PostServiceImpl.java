package com.tangdou.succulent.manager.service.post.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.model.Article;
import com.tangdou.succulent.manager.api.post.model.Post;
import com.tangdou.succulent.manager.mapper.*;
import com.tangdou.succulent.manager.service.post.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 12:44
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostMapper postMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PostCommentMapper postCommentMapper;

    @Resource
    private PostCollectionMapper postCollectionMapper;

    /**
     * 分页查询帖子信息
     *
     * @param post 查询条件
     * @return 帖子分页列表信息
     */
    @Override
    public PageInfo<Post> findByList(Post post) {
        PageHelper.startPage(post.getPage(), post.getPageSize());
        List<Post> list = postMapper.selectByList(post);
        for (Post item : list) {
            item.setUserAvatar(userMapper.selectById(item.getUserId()).getAvatar());
            item.setUserNickName(userMapper.selectById(item.getUserId()).getNickName());
            item.setModuleName(moduleMapper.selectById(item.getModuleId()).getName());
            item.setCommentNum(postCommentMapper.countByPostId(item.getId()));
            item.setCollectedNum(postCollectionMapper.countByPostId(item.getId()));
        }
        return new PageInfo<>(list);
    }

    /**
     * 帖子详情
     *
     * @param id  帖子id
     * @param deleted 删除状态
     * @return 帖子详情
     */
    @Override
    public Post detail(Integer id, Integer deleted) {
        Post post = postMapper.selectById(id, deleted);
        if (post == null) {
            return null;
        }
        post.setUserAvatar(userMapper.selectById(post.getUserId()).getAvatar());
        post.setUserNickName(userMapper.selectById(post.getUserId()).getNickName());
        post.setModuleName(moduleMapper.selectById(post.getModuleId()).getName());
        post.setCommentNum(postCommentMapper.countByPostId(post.getId()));
        post.setCollectedNum(postCollectionMapper.countByPostId(post.getId()));
        return post;
    }

    /**
     * 逻辑删除主题帖
     *
     * @param post 帖子信息
     */
    @Override
    public void delete(Post post) {
        postMapper.updateDeleteById(post);
    }
}
