package com.tangdou.succulent.manager.service.api;

import com.tangdou.succulent.manager.api.goods.GoodsCollectionServiceApi;
import com.tangdou.succulent.manager.api.goods.model.GoodsCollection;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.mapper.GoodsCollectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品收藏相关接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/25 16:30
 */
@Service
public class GoodsCollectionServiceApiImpl implements GoodsCollectionServiceApi {

    @Resource
    private GoodsCollectionMapper goodsCollectionMapper;

    /**
     * 用户收藏
     *
     * @param userId    用户id
     * @param goodsId 商品id
     * @return 响应状态
     */
    @Override
    public ResponseResult collection(Integer userId, Integer goodsId) {
        GoodsCollection info = new GoodsCollection();
        info.setUserId(userId);
        info.setGoodsId(goodsId);
        GoodsCollection goodsCollection = goodsCollectionMapper.selectByBothId(info);
        if (goodsCollection == null) {
            goodsCollectionMapper.insert(info);
        } else {
            goodsCollection.setStatus(0);
            goodsCollectionMapper.updateStatusById(goodsCollection);
        }
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 取消收藏
     *
     * @param userId    用户id
     * @param goodsId 商品id
     * @return 响应状态
     */
    @Override
    public ResponseResult unCollection(Integer userId, Integer goodsId) {
        GoodsCollection info = new GoodsCollection();
        info.setUserId(userId);
        info.setGoodsId(goodsId);
        GoodsCollection goodsCollection = goodsCollectionMapper.selectByBothId(info);
        goodsCollection.setStatus(1);
        goodsCollectionMapper.updateStatusById(goodsCollection);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 判断是否收藏
     *
     * @param userId    用户id
     * @param goodsId 商品id
     * @return 响应状态
     */
    @Override
    public ResponseResult<Boolean> isCollected(Integer userId, Integer goodsId) {
        Boolean isCollected = false;
        GoodsCollection info = new GoodsCollection();
        info.setUserId(userId);
        info.setGoodsId(goodsId);
        if (goodsCollectionMapper.countByBothId(info) != 0) {
            isCollected = true;
        }
        return new ResponseResult<>(isCollected);
    }

    /**
     * 查询商品收藏数量
     *
     * @param goodsId 商品id
     * @return 用户粉丝数量
     */
    @Override
    public ResponseResult<Integer> findCollectedNum(Integer goodsId) {
        return new ResponseResult<>(goodsCollectionMapper.countByGoodsId(goodsId));
    }
}
