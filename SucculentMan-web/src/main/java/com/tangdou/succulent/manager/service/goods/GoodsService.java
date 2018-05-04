package com.tangdou.succulent.manager.service.goods;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.model.Goods;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 20:24
 */
public interface GoodsService {

    /**
     * 分页查询商品信息
     * @param goods 查询条件
     * @return 商品分页列表信息
     */
    PageInfo<Goods> findByList(Goods goods);

    /**
     * 新增商品
     * @param goods 商品信息
     */
    void add(Goods goods);

    /**
     * 商品审核
     * @param goods 审核信息
     */
    void status(Goods goods);

    /**
     * 商品详情
     * @param id 商品编号
     * @param status 商品发布状态
     * @return 商品详情信息
     */
    Goods detail(Integer id, Integer status);

    /**
     * 编辑商品
     * @param goods 商品信息
     */
    void edit(Goods goods);
}
