package com.tangdou.succulent.manager.service.order.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.goods.model.Goods;
import com.tangdou.succulent.manager.api.order.model.Order;
import com.tangdou.succulent.manager.mapper.OrderMapper;
import com.tangdou.succulent.manager.service.order.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/8 13:11
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 分页查询订单信息
     *
     * @param order 查询条件
     * @return 订单信息
     */
    @Override
    public PageInfo<Order> findByList(Order order) {
        PageHelper.startPage(order.getPage(), order.getPageSize());
        List<Order> list = orderMapper.selectByList(order);
        return new PageInfo<>(list);
    }

    /**
     * 新增订单
     *
     * @param order 订单信息
     */
    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    /**
     * 修改订单状态
     *
     * @param order 修改信息
     */
    @Override
    public void updateStatus(Order order) {
        orderMapper.updateStatusById(order);
    }

    /**
     * 订单详情
     *
     * @param id 订单id
     * @return 订单详情
     */
    @Override
    public Order detail(Integer id) {
        return orderMapper.selectById(id);
    }
}
