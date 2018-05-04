package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.post.model.PostCollection;

import java.util.List;

/**
 * 帖子收藏
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/2 16:55
 */
public interface PostCollectionMapper {

    /**
     * 新增收藏信息
     * @param postCollection 收藏信息
     */
    void insert(PostCollection postCollection);

    /**
     * 修改收藏状态
     * @param postCollection 修改信息
     */
    void updateStatusById(PostCollection postCollection);

    /**
     * 通过双方id查询（判断是否建立过收藏关系）
     * @param postCollection 收藏信息
     * @return 关系信息
     */
    PostCollection selectByBothId(PostCollection postCollection);

    /**
     * 通过双方id统计（判断收藏状态）
     * @param postCollection 收藏信息
     * @return 统计数量
     */
    Integer countByBothId(PostCollection postCollection);

    /**
     * 通过用户id查询关系列表（查询收藏列表）
     * @param userId 用户id
     * @return 收藏关系列表信息
     */
    List<PostCollection> selectByUserId(Integer userId);

    /**
     * 通过被收藏用户id统计（查询帖子收藏数量）
     * @param postId 帖子id
     * @return 统计数量
     */
    Integer countByPostId(Integer postId);
}
