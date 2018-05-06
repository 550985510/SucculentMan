package com.tangdou.succulent.manager.api.goods.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 首页商品显示信息
 * @author 木叶丸
 * Created by 木叶丸 on 2018/5/6 15:03
 */
@Data
public class ShowGoods implements Serializable {

    private String imgOne;

    private String imgTwo;

    private String imgThree;

    private String imgFour;

    private String imgFive;

    private Integer sumOne;

    private Integer sumTwo;

    private Integer sumThree;

    private Integer sumFour;

    private Integer sumFive;
}
