package com.tangdou.succulent.manager.api.post;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.post.model.PostComment;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.service.post.PostCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/2 16:53
 */
@RestController
@RequestMapping("/api/post/comment")
public class PostCommentApiController {

    @Resource
    private PostCommentService postCommentService;

    /**
     * 分页查询文章评论信息
     * @param postComment 查询条件
     * @return 文章评论列表信息
     */
    @PostMapping("/list")
    public ResponseResult<PageInfo<PostComment>> findList(@RequestBody PostComment postComment) {
        PageInfo<PostComment> pageInfo = postCommentService.findByList(postComment);
        return new ResponseResult<>(pageInfo);
    }

    /**
     * 逻辑删除评论信息
     * @param postComment 评论信息
     * @return 操作状态
     */
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody PostComment postComment) {
        postCommentService.delete(postComment);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }
}
