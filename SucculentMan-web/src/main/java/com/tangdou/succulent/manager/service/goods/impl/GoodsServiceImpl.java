package com.tangdou.succulent.manager.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.model.Goods;
import com.tangdou.succulent.manager.mapper.GoodsCollectionMapper;
import com.tangdou.succulent.manager.mapper.GoodsCommentMapper;
import com.tangdou.succulent.manager.mapper.GoodsMapper;
import com.tangdou.succulent.manager.service.goods.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 20:24
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private GoodsCommentMapper goodsCommentMapper;

    @Resource
    private GoodsCollectionMapper goodsCollectionMapper;

    /**
     * 分页查询商品信息
     *
     * @param goods 查询条件
     * @return 商品分页列表信息
     */
    @Override
    public PageInfo<Goods> findByList(Goods goods) {
        PageHelper.startPage(goods.getPage(), goods.getPageSize());
        List<Goods> list = goodsMapper.selectByList(goods);
        for (Goods item : list) {
            item.setCommentNum(goodsCommentMapper.countByGoodsId(item.getId()));
            item.setCollectedNum(goodsCollectionMapper.countByGoodsId(item.getId()));
        }
        return new PageInfo<>(list);
    }

    /**
     * 新增商品
     *
     * @param goods 商品信息
     */
    @Override
    public void add(Goods goods) {
        goodsMapper.insert(goods);
    }

    /**
     * 商品审核
     *
     * @param goods 审核信息
     */
    @Override
    public void status(Goods goods) {
        goodsMapper.updateStatusById(goods);
    }

    /**
     * 商品详情
     *
     * @param id 商品编号
     * @param status 商品发布状态
     * @return 商品详情信息
     */
    @Override
    public Goods detail(Integer id, Integer status) {
        Goods goods = goodsMapper.selectById(id, status);
        if (goods == null) {
            return null;
        }
        goods.setCommentNum(goodsCommentMapper.countByGoodsId(goods.getId()));
        goods.setCollectedNum(goodsCollectionMapper.countByGoodsId(goods.getId()));
        return goods;
    }

    /**
     * 编辑商品
     *
     * @param goods 商品信息
     */
    @Override
    public void edit(Goods goods) {
        goodsMapper.update(goods);
    }
}
