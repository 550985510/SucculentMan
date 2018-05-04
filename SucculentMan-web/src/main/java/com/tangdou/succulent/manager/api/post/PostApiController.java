package com.tangdou.succulent.manager.api.post;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.api.post.model.Post;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.service.post.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 帖子相关
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/2 16:53
 */
@RestController
@RequestMapping("/api/post")
public class PostApiController {

    @Resource
    private PostService postService;

    /**
     * 分页查询文章信息
     * @param Post 查询条件
     * @return 文章列表信息
     */
    @PostMapping("/list")
    public ResponseResult<PageInfo<Post>> findList(@RequestBody Post Post) {
        PageInfo<Post> pageInfo = postService.findByList(Post);
        return new ResponseResult<>(pageInfo);
    }

    @PostMapping("delete")
    public ResponseResult delete(@RequestBody Post post) {
        postService.delete(post);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }
}
