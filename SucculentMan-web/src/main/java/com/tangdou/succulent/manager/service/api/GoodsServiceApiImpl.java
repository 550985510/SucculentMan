package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.GoodsServiceApi;
import com.tangdou.succulent.manager.api.goods.model.Goods;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.goods.model.ShowGoods;
import com.tangdou.succulent.manager.mapper.GoodsMapper;
import com.tangdou.succulent.manager.service.goods.GoodsService;
import org.apache.commons.lang3.RandomUtils;
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

    @Resource
    private GoodsMapper goodsMapper;

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

    /**
     * 首页显示商品信息
     *
     * @return 首页显示信息
     */
    @Override
    public ResponseResult<ShowGoods> showGoods() {
        ShowGoods showGoods = new ShowGoods();
        showGoods.setImgOne(goodsMapper.selectTop(1).getImg());
        showGoods.setImgTwo(goodsMapper.selectTop(3).getImg());
        showGoods.setImgThree(goodsMapper.selectTop(4).getImg());
        showGoods.setImgFour(goodsMapper.selectTop(5).getImg());
        showGoods.setImgFive(goodsMapper.selectTop(6).getImg());
        showGoods.setSumOne(RandomUtils.nextInt(6000, 10000) + 10000);
        showGoods.setSumTwo(RandomUtils.nextInt(6000, 10000) + 10000);
        showGoods.setSumThree(RandomUtils.nextInt(6000, 10000) + 10000);
        showGoods.setSumFour(RandomUtils.nextInt(6000, 10000) + 10000);
        showGoods.setSumFive(RandomUtils.nextInt(6000, 10000) + 10000);
        return new ResponseResult<>(showGoods);
    }
}
