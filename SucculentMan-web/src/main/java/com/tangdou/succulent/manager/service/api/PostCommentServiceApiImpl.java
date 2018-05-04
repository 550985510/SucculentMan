package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.post.PostCommentServiceApi;
import com.tangdou.succulent.manager.api.post.model.PostComment;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.mapper.PostCommentMapper;
import com.tangdou.succulent.manager.service.post.PostCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 帖子回帖接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:36
 */
@Service
public class PostCommentServiceApiImpl implements PostCommentServiceApi {

    @Resource
    private PostCommentMapper postCommentMapper;

    @Resource
    private PostCommentService postCommentService;

    /**
     * 新增回帖
     *
     * @param postComment 回帖信息
     * @return 操作状态
     */
    @Override
    public ResponseResult add(PostComment postComment) {
        postCommentMapper.insert(postComment);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 分页查询回帖信息列表
     *
     * @param postComment 查询条件
     * @return 回帖内容列表信息
     */
    @Override
    public ResponseResult<PageInfo<PostComment>> findPage(PostComment postComment) {
        PageInfo<PostComment> pageInfo = postCommentService.findByList(postComment);
        return new ResponseResult<>(pageInfo);
    }
}
