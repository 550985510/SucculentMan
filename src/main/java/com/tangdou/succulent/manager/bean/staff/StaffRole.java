package com.tangdou.succulent.manager.bean.staff;

import lombok.Data;

import java.util.Date;

/**
 * 员工角色
 * @author 木叶丸
 * @date 2018/2/23
 */
@Data
public class StaffRole {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色简介
     */
    private String brief;

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
