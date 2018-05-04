package com.tangdou.succulent.manager.api.goods.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品评论表
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/1 16:28
 */
@Data
public class GoodsComment implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 逻辑删除状态 0:未删除 1:删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 商品标题
     */
    private String goodsName;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer pageSize;
}
