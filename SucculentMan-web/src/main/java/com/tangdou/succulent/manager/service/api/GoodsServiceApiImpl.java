package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.GoodsServiceApi;
import com.tangdou.succulent.manager.api.goods.model.Goods;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.service.goods.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 木叶丸
 * @date 2018/3/29
 */
@Service
public class GoodsServiceApiImpl implements GoodsServiceApi {

    @Resource
    private GoodsService goodsService;

    /**
     * 分页查询文章信息
     *
     * @param goods 查询条件
     * @return 文章分页信息
     */
    @Override
    public ResponseResult<PageInfo<Goods>> list(Goods goods) {
        return new ResponseResult<>(goodsService.findByList(goods));
    }

    /**
     * 文章详情
     *
     * @param goodsId 文章id
     * @param status 文章发布状态
     * @return 文章信息
     */
    @Override
    public ResponseResult<Goods> detail(Integer goodsId, Integer status) {
        Goods goods = goodsService.detail(goodsId, status);
        return new ResponseResult<>(goods);
    }
}
