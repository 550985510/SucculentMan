package com.tangdou.succulent.manager.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页信息
 * @author 木叶丸
 * @date 2018/2/23
 */
@Data
public class PageVo implements Serializable {

    private Integer page;

    private Integer pageSize;

}
