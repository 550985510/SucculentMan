package com.tangdou.succulent.manager.service.api;

import com.tangdou.succulent.manager.api.post.PostCollectionServiceApi;
import com.tangdou.succulent.manager.api.post.model.PostCollection;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.mapper.PostCollectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 帖子收藏相关接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/25 16:30
 */
@Service
public class PostCollectionServiceApiImpl implements PostCollectionServiceApi {

    @Resource
    private PostCollectionMapper postCollectionMapper;

    /**
     * 用户收藏
     *
     * @param userId    用户id
     * @param postId 帖子id
     * @return 响应状态
     */
    @Override
    public ResponseResult collection(Integer userId, Integer postId) {
        PostCollection info = new PostCollection();
        info.setUserId(userId);
        info.setPostId(postId);
        PostCollection postCollection = postCollectionMapper.selectByBothId(info);
        if (postCollection == null) {
            postCollectionMapper.insert(info);
        } else {
            postCollection.setStatus(0);
            postCollectionMapper.updateStatusById(postCollection);
        }
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 取消收藏
     *
     * @param userId    用户id
     * @param postId 帖子id
     * @return 响应状态
     */
    @Override
    public ResponseResult unCollection(Integer userId, Integer postId) {
        PostCollection info = new PostCollection();
        info.setUserId(userId);
        info.setPostId(postId);
        PostCollection postCollection = postCollectionMapper.selectByBothId(info);
        postCollection.setStatus(1);
        postCollectionMapper.updateStatusById(postCollection);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 判断是否收藏
     *
     * @param userId    用户id
     * @param postId 帖子id
     * @return 响应状态
     */
    @Override
    public ResponseResult<Boolean> isCollected(Integer userId, Integer postId) {
        Boolean isCollected = false;
        PostCollection info = new PostCollection();
        info.setUserId(userId);
        info.setPostId(postId);
        if (postCollectionMapper.countByBothId(info) != 0) {
            isCollected = true;
        }
        return new ResponseResult<>(isCollected);
    }

    /**
     * 查询帖子收藏数量
     *
     * @param postId 帖子id
     * @return 用户粉丝数量
     */
    @Override
    public ResponseResult<Integer> findCollectedNum(Integer postId) {
        return new ResponseResult<>(postCollectionMapper.countByPostId(postId));
    }
}
