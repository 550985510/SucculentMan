package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.api.post.PostServiceApi;
import com.tangdou.succulent.manager.api.post.model.Post;
import com.tangdou.succulent.manager.mapper.PostMapper;
import com.tangdou.succulent.manager.service.post.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 13:15
 */
@Service
public class PostServiceApiImpl implements PostServiceApi {

    @Resource
    private PostService postService;

    @Resource
    private PostMapper postMapper;

    /**
     * 分页查询帖子信息
     *
     * @param post 查询条件
     * @return 帖子分页信息
     */
    @Override
    public ResponseResult<PageInfo<Post>> list(Post post) {
        return new ResponseResult<>(postService.findByList(post));
    }

    /**
     * 帖子详情
     *
     * @param postId  帖子id
     * @param deleted 帖子发布状态
     * @return 帖子信息
     */
    @Override
    public ResponseResult<Post> detail(Integer postId, Integer deleted) {
        return new ResponseResult<>(postService.detail(postId, deleted));
    }

    /**
     * 发表新帖
     *
     * @param post 帖子内容
     * @return 操作状态
     */
    @Override
    public ResponseResult add(Post post) {
        postMapper.insert(post);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }
}
