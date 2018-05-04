package com.tangdou.succulent.manager.service.post;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.post.model.Post; /**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 12:43
 */
public interface PostService {

    /**
     * 分页查询帖子信息
     * @param post 查询条件
     * @return 帖子分页列表信息
     */
    PageInfo<Post> findByList(Post post);

    /**
     * 帖子详情
     * @param id 帖子id
     * @param deleted 删除状态
     * @return 帖子详情
     */
    Post detail(Integer id, Integer deleted);

    /**
     * 逻辑删除主题帖
     * @param post 帖子信息
     */
    void delete(Post post);
}
