package com.tangdou.succulent.manager.service.goods;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.model.GoodsComment;

/**
 * 商品评论
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:36
 */
public interface GoodsCommentService {

    /**
     * 分页查询商品评论列表信息
     * @param goodsComment 查询条件
     * @return 商品评论列表信息
     */
    PageInfo<GoodsComment> findByList(GoodsComment goodsComment);

    /**
     * 逻辑删除评论信息
     * @param goodsComment 评论信息
     */
    void delete(GoodsComment goodsComment);
}
