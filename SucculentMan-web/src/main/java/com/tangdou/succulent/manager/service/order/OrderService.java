package com.tangdou.succulent.manager.service.order;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.order.model.Order;

/**
 * 订单相关
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/8 13:11
 */
public interface OrderService {

    /**
     * 分页查询订单信息
     * @param order 查询条件
     * @return 订单信息
     */
    PageInfo<Order> findByList(Order order);

    /**
     * 新增订单
     * @param order 订单信息
     */
    void add(Order order);

    /**
     * 修改订单状态
     * @param order 修改信息
     */
    void updateStatus(Order order);

    /**
     * 订单详情
     * @param id 订单id
     * @return 订单详情
     */
    Order detail(Integer id);
}
