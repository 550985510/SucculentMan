package com.tangdou.succulent.manager.api.order;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.order.model.Order;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/8 13:00
 */
public interface OrderServiceApi {

    /**
     * 分页查询订单信息
     * @param order 查询条件
     * @return 订单信息
     */
    ResponseResult<PageInfo<Order>> list(Order order);

    /**
     * 修改订单状态
     * @param order 修改内容
     * @return 操作状态
     */
    ResponseResult edit(Order order);

    /**
     * 新增订单
     * @param order 订单信息
     * @return 操作状态
     */
    ResponseResult add(Order order);
}
