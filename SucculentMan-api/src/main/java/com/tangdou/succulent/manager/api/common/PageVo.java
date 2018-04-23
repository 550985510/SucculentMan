package com.tangdou.succulent.manager.api.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页对象
 * @author 木叶丸
 * Created by 木叶丸 on 2018/4/23 13:06
 */
@Data
public class PageVo implements Serializable {

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 分页大小
     */
    private Integer pageSize;
}
