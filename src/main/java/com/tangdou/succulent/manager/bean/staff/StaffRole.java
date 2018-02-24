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

    private Integer id;

    private String name;

    private String brief;

    private Integer deleted;

    private Date createdTime;

    private String createdBy;

    private Date modifiedTime;

    private String modifiedBy;

}
