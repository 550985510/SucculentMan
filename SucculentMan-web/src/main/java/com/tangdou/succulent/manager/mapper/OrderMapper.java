package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.api.order.model.Order;

import java.util.List;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/8 13:12
 */
public interface OrderMapper {

    /**
     * 新增订单
     * @param order 订单信息
     */
    void insert(Order order);

    /**
     * 修改订单状态
     * @param order 订单状态
     */
    void updateStatusById(Order order);

    /**
     * 条件查询订单列表信息
     * @param order 查询条件
     * @return 订单列表信息
     */
    List<Order> selectByList(Order order);

    /**
     * 通过id查询订单
     * @param id 订单id
     * @return 订单信息
     */
    Order selectById(Integer id);
}
