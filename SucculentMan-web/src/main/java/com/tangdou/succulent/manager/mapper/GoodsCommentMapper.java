package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.goods.model.GoodsComment;

import java.util.List;

/**
 * 商品评论
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:37
 */
public interface GoodsCommentMapper {

    /**
     * 新增评论
     * @param goodsComment 评论内容
     */
    void insert(GoodsComment goodsComment);

    /**
     * 修改评论内容（删除状态）
     * @param goodsComment 修改内容
     */
    void updateById(GoodsComment goodsComment);

    /**
     * 通过主键id查询评论
     * @param id 主键id
     * @return 评论信息
     */
    GoodsComment selectById(Integer id);

    /**
     * 查询评论列表信息
     * @param goodsComment 查询条件
     * @return 评论列表信息
     */
    List<GoodsComment> selectList(GoodsComment goodsComment);

    /**
     * 统计商品评论数量
     * @param goodsId 商品id
     * @return 商品评论数量
  */
    Integer countByGoodsId(Integer goodsId);
}
