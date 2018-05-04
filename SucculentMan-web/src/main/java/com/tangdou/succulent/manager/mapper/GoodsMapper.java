package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.goods.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 20:25
 */
public interface GoodsMapper {

    /**
     * 新增商品信息
     * @param goods 商品信息
     */
    void insert(Goods goods);

    /**
     * 通过主键id修改商品上架状态
     * @param goods 修改内容
     */
    void updateStatusById(Goods goods);

    /**
     * 修改商品信息
     * @param goods 修改内容
     */
    void update(Goods goods);

    /**
     * 条件查询商品列表信息
     * @param goods 查询条件
     * @return 商品列表信息
     */
    List<Goods> selectByList(Goods goods);

    /**
     * 通过主键id和上架状态查询商品
     * @param id 主键id
     * @param status 上架状态
     * @return 商品信息
     */
    Goods selectById(@Param("id") Integer id, @Param("status") Integer status);
}
