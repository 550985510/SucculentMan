package com.tangdou.succulent.manager.bean.module;

import lombok.Data;

import java.util.Date;

/**
 * 论坛模块
 * @author 木叶丸
 * @date 2018/3/16
 */
@Data
public class Module {

    /**
     * 模块id
     */
    private Integer id;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 类型 0:文章,1:帖子'
     */
    private Integer type;

    /**
     * 逻辑删除 1:删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 最后修改人
     */
    private String modifiedBy;
}
