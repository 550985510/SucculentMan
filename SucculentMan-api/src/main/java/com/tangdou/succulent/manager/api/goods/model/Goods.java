package com.tangdou.succulent.manager.api.goods.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/4 20:10
 */
@Data
public class Goods implements Serializable {

    /**
     * 商品编号
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品图片地址
     */
    private String img;

    /**
     * 商品单价
     */
    private Double price;

    /**
     * 商品上架状态 0:下架 1:上架
     */
    private Integer status;

    /**
     * 商品类型 1:多肉 2:花器 3:盆器 4:配土 5:资材 6:服务
     */
    private Integer type;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 最后修改人
     */
    private String modifiedBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 收藏数量
     */
    private Integer collectedNum;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer pageSize;
}
