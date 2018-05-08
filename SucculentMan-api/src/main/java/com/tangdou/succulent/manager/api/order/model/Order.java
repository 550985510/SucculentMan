package com.tangdou.succulent.manager.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/8 13:00
 */
@Data
public class Order implements Serializable {

    /**
     * 订单编号
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户真实姓名
     */
    private String userName;

    /**
     * 收货地址
     */
    private String location;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 订单金额
     */
    private Double amount;

    /**
     * 订单状态 0:待处理 1:已发货 2:用户退货 3:交易完成
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer pageSize;
}
