package com.tangdou.succulent.manager.bean.staff;

import lombok.Data;

import java.util.Date;

/**
 * @author 木叶丸
 * @date 2018/2/24
 */
@Data
public class Department {

    /**
     * 部门id
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 逻辑删除状态
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    /**
     * 最后修改人
     */
    private String modifiedBy;

}
