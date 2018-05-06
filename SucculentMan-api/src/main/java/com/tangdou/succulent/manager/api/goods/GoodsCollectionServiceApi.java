package com.tangdou.succulent.manager.api.goods;

import com.tangdou.succulent.manager.api.common.ResponseResult;

/**
 * 商品收藏相关接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/25 16:30
 */
public interface GoodsCollectionServiceApi {

    /**
     * 用户收藏
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 响应状态
     */
    ResponseResult collection(Integer userId, Integer goodsId);

    /**
     * 取消收藏
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 响应状态
     */
    ResponseResult unCollection(Integer userId, Integer goodsId);

    /**
     * 判断是否收藏
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 响应状态
     */
    ResponseResult<Boolean> isCollected(Integer userId, Integer goodsId);

    /**
     * 查询商品收藏数量
     * @param goodsId 商品id
     * @return 用户粉丝数量
     */
    ResponseResult<Integer> findCollectedNum(Integer goodsId);
}
