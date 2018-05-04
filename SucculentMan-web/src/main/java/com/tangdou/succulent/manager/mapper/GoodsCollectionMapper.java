package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.goods.model.GoodsCollection;

import java.util.List;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/25 16:31
 */
public interface GoodsCollectionMapper {

    /**
     * 新增收藏信息
     * @param goodsCollection 收藏信息
     */
    void insert(GoodsCollection goodsCollection);

    /**
     * 修改收藏状态
     * @param goodsCollection 修改信息
     */
    void updateStatusById(GoodsCollection goodsCollection);

    /**
     * 通过双方id查询（判断是否建立过收藏关系）
     * @param goodsCollection 收藏信息
     * @return 关系信息
     */
    GoodsCollection selectByBothId(GoodsCollection goodsCollection);

    /**
     * 通过双方id统计（判断收藏状态）
     * @param goodsCollection 收藏信息
     * @return 统计数量
     */
    Integer countByBothId(GoodsCollection goodsCollection);

    /**
     * 通过用户id查询关系列表（查询收藏列表）
     * @param userId 用户id
     * @return 收藏关系列表信息
     */
    List<GoodsCollection> selectByUserId(Integer userId);

    /**
     * 通过被收藏用户id统计（查询商品收藏数量）
     * @param goodsId 商品id
     * @return 统计数量
     */
    Integer countByGoodsId(Integer goodsId);
}
