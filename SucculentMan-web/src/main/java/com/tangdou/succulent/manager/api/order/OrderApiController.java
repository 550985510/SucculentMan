package com.tangdou.succulent.manager.api.order;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.api.order.model.Order;
import com.tangdou.succulent.manager.service.order.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/8 13:08
 */
@RestController
@RequestMapping("/api/order")
public class OrderApiController {

    @Resource
    private OrderService orderService;

    @PostMapping("/list")
    public ResponseResult<PageInfo<Order>> findAll(@RequestBody Order order) {
        PageInfo<Order> pageInfo = orderService.findByList(order);
        return new ResponseResult<>(pageInfo);
    }

    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody Order order) {
        orderService.updateStatus(order);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

}
