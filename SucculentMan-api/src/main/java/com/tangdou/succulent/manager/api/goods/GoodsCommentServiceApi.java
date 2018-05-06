package com.tangdou.succulent.manager.api.goods;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.model.GoodsComment;
import com.tangdou.succulent.manager.api.common.ResponseResult;

/**
 * 商品评论接口
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:34
 */
public interface GoodsCommentServiceApi {

    /**
     * 新增评论
     * @param goodsComment 评论信息
     * @return 操作状态
     */
    ResponseResult add(GoodsComment goodsComment);

    /**
     * 分页查询评论信息列表
     * @param goodsComment 查询条件
     * @return 评论内容列表信息
     */
    ResponseResult<PageInfo<GoodsComment>> findPage(GoodsComment goodsComment);
}
