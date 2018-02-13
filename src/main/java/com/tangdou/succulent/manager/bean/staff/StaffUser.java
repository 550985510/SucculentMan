package com.tangdou.succulent.manager.bean.staff;

import lombok.Data;

import java.util.Date;

/**
 * @author cuihang
 * @date 2018/2/13
 */

@Data
public class StaffUser {

    private Integer id;

    private String userName;

    private String passWord;

    private String realName;

    private Integer gender;

    private String mobile;

    private Integer roleId;

    private String roleName;

    private Integer deptId;

    private String deptName;

    private Integer status;

    private Date createdTime;

    private String createdBy;

    private Date modifiedTime;

    private String modifiedBy;

}
