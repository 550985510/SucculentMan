package com.tangdou.succulent.manager.service.article.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.model.ArticleComment;
import com.tangdou.succulent.manager.mapper.ArticleCommentMapper;
import com.tangdou.succulent.manager.mapper.ArticleMapper;
import com.tangdou.succulent.manager.mapper.UserMapper;
import com.tangdou.succulent.manager.service.article.ArticleCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章评论相关
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:37
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Resource
    private ArticleCommentMapper articleCommentMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ArticleMapper articleMapper;

    /**
     * 分页查询文章评论列表信息
     *
     * @param articleComment 查询条件
     * @return 文章评论列表信息
     */
    @Override
    public PageInfo<ArticleComment> findByList(ArticleComment articleComment) {
        PageHelper.startPage(articleComment.getPage(), articleComment.getPageSize());
        List<ArticleComment> list = articleCommentMapper.selectList(articleComment);
        for (ArticleComment item : list) {
            item.setUserAvatar(userMapper.selectById(item.getUserId()).getAvatar());
            item.setUserNickName(userMapper.selectById(item.getUserId()).getNickName());
            item.setArticleTitle(articleMapper.selectById(item.getArticleId(),null).getTitle());
        }
        return new PageInfo<>(list);
    }

    /**
     * 逻辑删除评论信息
     *
     * @param articleComment 评论信息
     */
    @Override
    public void delete(ArticleComment articleComment) {
        articleCommentMapper.updateById(articleComment);
    }
}
