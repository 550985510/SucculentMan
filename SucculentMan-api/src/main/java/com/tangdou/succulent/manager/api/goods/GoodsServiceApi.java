package com.tangdou.succulent.manager.api.goods;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.article.model.Article;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.goods.model.Goods;
import com.tangdou.succulent.manager.api.goods.model.ShowGoods;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 20:10
 */
public interface GoodsServiceApi {

    /**
     * 分页查询商品信息
     * @param goods 查询条件
     * @return 商品分页信息
     */
    ResponseResult<PageInfo<Goods>> list(Goods goods);

    /**
     * 商品详情
     * @param goodsId 商品id
     * @param status 商品发布状态
     * @return 商品信息
     */
    ResponseResult<Goods> detail(Integer goodsId, Integer status);

    /**
     * 首页显示商品信息
     * @return 首页显示信息
     */
    ResponseResult<ShowGoods> showGoods();
}
