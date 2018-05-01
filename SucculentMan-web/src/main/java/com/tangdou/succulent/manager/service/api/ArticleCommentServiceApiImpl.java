package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.ArticleCommentServiceApi;
import com.tangdou.succulent.manager.api.article.model.ArticleComment;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.mapper.ArticleCommentMapper;
import com.tangdou.succulent.manager.service.article.ArticleCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 文章评论接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:36
 */
@Service
public class ArticleCommentServiceApiImpl implements ArticleCommentServiceApi {

    @Resource
    private ArticleCommentMapper articleCommentMapper;

    @Resource
    private ArticleCommentService articleCommentService;

    /**
     * 新增评论
     *
     * @param articleComment 评论信息
     * @return 操作状态
     */
    @Override
    public ResponseResult add(ArticleComment articleComment) {
        articleCommentMapper.insert(articleComment);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 分页查询评论信息列表
     *
     * @param articleComment 查询条件
     * @return 评论内容列表信息
     */
    @Override
    public ResponseResult<PageInfo<ArticleComment>> findPage(ArticleComment articleComment) {
        PageInfo<ArticleComment> pageInfo = articleCommentService.findByList(articleComment);
        return new ResponseResult<>(pageInfo);
    }
}
