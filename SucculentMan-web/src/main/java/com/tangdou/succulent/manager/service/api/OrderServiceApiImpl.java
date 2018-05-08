package com.tangdou.succulent.manager.service.api;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.api.order.OrderServiceApi;
import com.tangdou.succulent.manager.api.order.model.Order;
import com.tangdou.succulent.manager.mapper.OrderMapper;
import com.tangdou.succulent.manager.service.order.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/8 13:10
 */
@Service
public class OrderServiceApiImpl implements OrderServiceApi {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderMapper orderMapper;

    /**
     * 分页查询订单信息
     *
     * @param order 查询条件
     * @return 订单信息
     */
    @Override
    public ResponseResult<PageInfo<Order>> list(Order order) {
        PageInfo<Order> pageInfo = orderService.findByList(order);
        return new ResponseResult<>(pageInfo);
    }

    /**
     * 修改订单状态
     *
     * @param order 修改内容
     * @return 操作状态
     */
    @Override
    public ResponseResult edit(Order order) {
        orderService.updateStatus(order);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 新增订单
     *
     * @param order 订单信息
     * @return 操作状态
     */
    @Override
    public ResponseResult add(Order order) {
        orderMapper.insert(order);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }
}
