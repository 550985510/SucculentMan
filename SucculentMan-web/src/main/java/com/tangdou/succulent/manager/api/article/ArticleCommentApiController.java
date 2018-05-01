package com.tangdou.succulent.manager.api.article;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.model.Article;
import com.tangdou.succulent.manager.api.article.model.ArticleComment;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.service.article.ArticleCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章评论相关
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:35
 */
@RestController
@RequestMapping("/api/article/comment")
public class ArticleCommentApiController {

    @Resource
    private ArticleCommentService articleCommentService;

    /**
     * 分页查询文章评论信息
     * @param articleComment 查询条件
     * @return 文章评论列表信息
     */
    @PostMapping("/list")
    public ResponseResult<PageInfo<ArticleComment>> findList(@RequestBody ArticleComment articleComment) {
        PageInfo<ArticleComment> pageInfo = articleCommentService.findByList(articleComment);
        return new ResponseResult<>(pageInfo);
    }

    /**
     * 逻辑删除评论信息
     * @param articleComment 评论信息
     * @return 操作状态
     */
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody ArticleComment articleComment) {
        articleCommentService.delete(articleComment);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }
}
