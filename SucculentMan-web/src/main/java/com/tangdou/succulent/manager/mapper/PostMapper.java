package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.post.model.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/2 16:55
 */
public interface PostMapper {

    /**
     * 插入一条帖子记录
     * @param post 帖子信息
     */
    void insert(Post post);

    /**
     * 条件查询帖子列表信息
     * @param post 查询条件
     * @return 帖子列表信息
     */
    List<Post> selectByList(Post post);

    /**
     * 通过id查询帖子信息
     * @param id 主键id
     * @param deleted 删除状态
     * @return 帖子信息
     */
    Post selectById(@Param("id") Integer id, @Param("deleted") Integer deleted);

    /**
     * 通过id修改逻辑删除字段
     * @param post 修改内容
     */
    void updateDeleteById(Post post);
}
